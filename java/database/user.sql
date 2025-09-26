-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER spotify_fullstack_owner
WITH PASSWORD 'spotify';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO spotify_fullstack_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO spotify_fullstack_owner;

CREATE USER spotify_fullstack_appuser
WITH PASSWORD 'spotify';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO spotify_fullstack_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO spotify_fullstack_appuser;
