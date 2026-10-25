SET search_path TO streamix_profile;

-- Retrieve all profiles belonging to a user
CREATE INDEX idx_profiles_user_id
    ON profiles (user_id);

-- Support lookups and foreign-key maintenance for avatar references
CREATE INDEX idx_profiles_avatar_id
    ON profiles (avatar_id);