INSERT INTO article(id, title, content) VALUES(1, '1', 'first content');
INSERT INTO article(id, title, content) VALUES(2, '2', 'second content');
INSERT INTO article(id, title, content) VALUES(3, '3', 'third content');

-- Article Dummy Data
INSERT INTO article(id, title, content) VALUES(4, 'java programming', 'java programming');
INSERT INTO article(id, title, content) VALUES(5, 'spring framework', 'spring framework');

-- 4번 게시글의 댓글들
INSERT INTO comment(id, body, nickname, article_id) VALUES (1, 'Java1', 'noop1', 4);
INSERT INTO comment(id, body, nickname, article_id) VALUES (2, 'Java2', 'noop2', 4);
INSERT INTO comment(id, body, nickname, article_id) VALUES (3, 'Java3', 'noop3', 4);

-- 5번 게시글의 댓글들
INSERT INTO comment(id, body, nickname, article_id) VALUES (4, 'spring gosu', 'si', 5);
INSERT INTO comment(id, body, nickname, article_id) VALUES (5, 'spring chobo', 'sm', 5);
INSERT INTO comment(id, body, nickname, article_id) VALUES (6, 'sualmot', 'noop3', 5);