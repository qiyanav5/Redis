create table province
(
    id   int auto_increment
        primary key,
    name varchar(20) not null
);

INSERT INTO day19.province (id, name) VALUES (1, '北京');
INSERT INTO day19.province (id, name) VALUES (2, '上海');
INSERT INTO day19.province (id, name) VALUES (3, '广州');
INSERT INTO day19.province (id, name) VALUES (4, '贵州');
INSERT INTO day19.province (id, name) VALUES (5, '陕西');