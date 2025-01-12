ALTER TABLE dragon DROP CONSTRAINT dragon_coordinates_id_fkey;

ALTER TABLE dragon
    ADD CONSTRAINT dragon_coordinates_id_fkey FOREIGN KEY (coordinates_id)
        REFERENCES coordinates (id) ON DELETE RESTRICT;