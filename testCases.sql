USE travel_agency;

-- VEHICLE ASSIGNMENT TESTS (3.1.3.1)

-- Successful Assignment
-- Trip 1 (Driver D000000001 has 'D' license). Vehicle 1 (BUS, 50 seats).
SELECT veh_id, veh_status, veh_total_km FROM vehicle WHERE veh_id = 1;
CALL assign_vehicle_to_trip(1, 1, 130000);

-- Verify Success (Status -> IN_USE, tr_veh_id -> 1)
SELECT veh_id, veh_status, veh_total_km FROM vehicle WHERE veh_id = 1;
SELECT tr_id, tr_veh_id FROM trip WHERE tr_id = 1;

-- Failed Assignment (Not Enough Seats)
 CALL assign_vehicle_to_trip(4, 6, 42500);

-- Failed Assignment (License Mismatch)
-- Trip 9 driver 'D0000000102' ('B' license) Vehicle 2 'BUS' (>9 seats, needs D or C).
CALL assign_vehicle_to_trip(9, 2, 98500);

-- Failed Assignment (Time Overlap)
-- Trip 1 (June 10-13). Vehicle 3 is pre-assigned to Trip 15 (June 11-12) via our setup.
UPDATE trip SET tr_departure = '2026-06-11 10:00:00', tr_return = '2026-06-12 20:00:00', tr_veh_id = 3 WHERE tr_id = 6;
CALL assign_vehicle_to_trip(1, 3, 74500);







-- SEARCH & BOOKING TESTS (3.1.3.2 & 3.1.3.3)

-- Successful Search 3.1.3.2
SET @found_acc_id = 0;
CALL SP_SEARCH(1, '2026-08-01 12:00:00', '2026-08-05 12:00:00', 2, @found_acc_id);
SELECT @found_acc_id as 'Found Accommodation ID';


-- Successful Booking 3.1.3.3
-- 2 rooms Trip 8 (Napoli).
CALL BOOKING(8, 2);

-- Verify booking was inserted & cost calculated by Trigger
SELECT ta_trip_id, ta_acc_id, ta_rooms, ta_total_cost 
FROM trip_accommodation WHERE ta_trip_id = 8;


-- Failed Booking (Rollback Test)
SELECT to_tr_id, to_dst_id, to_arrival, to_sequence 
FROM travel_to WHERE to_tr_id = 8;
CALL BOOKING(8, 50);

-- Verify Rollback (No rows should exist for Trip 3)
SELECT * FROM trip_accommodation WHERE ta_trip_id = 8;






-- ANALYTICS (3.1.3.4) 

-- Revenue 2023
CALL sum_revenue_between_dates('2023-01-01', '2023-12-31');

-- Trips Destination Count
CALL trips_by_destinations_count(3);






-- TRIGGER TESTS (3.1.4)

-- Log Trigger (3.1.4.1)
UPDATE customer SET cust_phone = '+306999999999' WHERE cust_id = 1;
SELECT * FROM system_log ORDER BY log_id DESC LIMIT 1;


-- Trip Completion Trigger (3.1.4.3)
UPDATE trip SET tr_final_km = 135000, tr_status = 'COMPLETED' WHERE tr_id = 1;

-- Verify Vehicle 1 is now 'AVAILABLE' and has updated km
SELECT veh_id, veh_status, veh_total_km FROM vehicle WHERE veh_id = 1;