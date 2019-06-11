DROP TABLE IF EXISTS `user_option`, `user`, `paper`, `subject`, `option`;

CREATE TABLE IF NOT EXISTS `user`
(
    id            INTEGER PRIMARY KEY AUTO_INCREMENT,
    username      CHAR(30) NOT NULL,
    password      CHAR(30) NOT NULL,
    isAdmin       BOOLEAN  DEFAULT FALSE,
    createTime    DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `paper`
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    title       CHAR(50) NOT NULL,
    isPublish   BOOLEAN DEFAULT TRUE,
    publishTime DATETIME,
    endTime     DATETIME,
    userId      INTEGER  NOT NULL, -- 管理员ID
    FOREIGN KEY (userId) REFERENCES `user` (id)
);


CREATE TABLE IF NOT EXISTS `subject`
(
    id      INTEGER PRIMARY KEY AUTO_INCREMENT,
    `index` INT DEFAULT 0, -- 序号
    title   CHAR(50) NOT NULL,
    type    CHAR(30) NOT NULL,
    paperId INTEGER  NOT NULL,
    FOREIGN KEY (paperId) REFERENCES `paper` (id)
);

CREATE TABLE IF NOT EXISTS `option`
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    `index`   INT DEFAULT 0, -- 序号
    content   TEXT(500) NOT NULL,
    subjectId INTEGER   NOT NULL,
    FOREIGN KEY (subjectId) REFERENCES `subject` (id)
);

CREATE TABLE IF NOT EXISTS `user_option`
(
    id       INTEGER PRIMARY KEY AUTO_INCREMENT,
    userId   INTEGER NOT NULL,
    optionId INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES `user` (id), -- 普通用户
    FOREIGN KEY (optionId) REFERENCES `option` (id)
);

