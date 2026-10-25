SET search_path TO streamix_profile;

ALTER TABLE profiles
    ADD CONSTRAINT uq_profiles_user_profile_name UNIQUE (user_id, profile_name);
