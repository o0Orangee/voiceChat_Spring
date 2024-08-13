CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_create_date TIMESTAMP,
    user_icon BLOB,
    user_ment VARCHAR(255),
    user_name VARCHAR(255),
    user_nickname VARCHAR(255),
    user_provider VARCHAR(255),
    user_provider_id VARCHAR(255),
    user_update_date TIMESTAMP,
    user_uuid VARCHAR(255)
);