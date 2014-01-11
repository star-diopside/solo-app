
/* Drop Tables */

DROP TABLE IF EXISTS sessions;




/* Create Tables */

CREATE TABLE sessions
(
	id varchar(32) NOT NULL,
	data bytea NOT NULL,
	modified_time bigint NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;



/* Comments */

COMMENT ON TABLE sessions IS 'セッション';
COMMENT ON COLUMN sessions.id IS 'セッションID';
COMMENT ON COLUMN sessions.data IS 'セッションデータ';
COMMENT ON COLUMN sessions.modified_time IS '変更時刻';



