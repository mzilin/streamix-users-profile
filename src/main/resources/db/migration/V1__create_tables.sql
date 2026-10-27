SET search_path TO streamix_profile;

CREATE TABLE avatars (
    id          UUID         NOT NULL DEFAULT gen_random_uuid(),
    avatar_name VARCHAR(255) NOT NULL,
    object_key  TEXT         NOT NULL,
    image_url   TEXT         NOT NULL,

    CONSTRAINT pk_avatars PRIMARY KEY (id),
    CONSTRAINT uq_avatars_avatar_name UNIQUE (avatar_name)
);

CREATE TABLE profiles (
    id           UUID         NOT NULL DEFAULT gen_random_uuid(),
    user_id      UUID         NOT NULL,
    profile_name VARCHAR(255) NOT NULL,
    avatar_id    UUID,
    is_kid       BOOLEAN      NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_profiles           PRIMARY KEY (id),
    CONSTRAINT fk_profiles_avatar_id FOREIGN KEY (avatar_id) REFERENCES avatars (id) ON DELETE SET NULL
);
