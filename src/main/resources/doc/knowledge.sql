DROP TABLE IF EXISTS knowledge;

CREATE TABLE knowledge
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50)  NOT NULL,                                                       -- 知识库名称
    info        VARCHAR(200),                                                                -- 知识库简介(用于Agent)
    vs_type     VARCHAR(50)  NOT NULL,                                                       -- 向量库类型
    embed_model VARCHAR(50)  NOT NULL,                                                       -- 嵌入模型名称
    file_count  INT UNSIGNED          DEFAULT 0,                                             -- 文件数量
    corpus_id   INT UNSIGNED NOT NULL,                                                       -- 语料库ID
    create_by   VARCHAR(8)   NOT NULL,                                                       -- 创建者
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_by   VARCHAR(8)   NOT NULL,                                                       -- 修改者
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 修改时间
    PRIMARY KEY (id),
    INDEX idx_corpus_id (corpus_id),
    FOREIGN KEY (corpus_id) REFERENCES corpus (id)
) AUTO_INCREMENT = 3209847;

INSERT INTO knowledge (name, info, vs_type, embed_model, corpus_id, create_by, update_by)
VALUES ('Tech Terms', 'Technical terminologies and concepts', 'semantic', 'bert-base-uncased', 3209847, '武星', '武星'),
       ('Medical Glossary', 'Comprehensive medical terms database', 'semantic', 'bio-bert-v1.1', 3209847, '武星',
        '武星'),
       ('World History', 'Detailed events from global history', 'semantic', 'roberta-base', 3209847, '武星', '武星'),
       ('Programming Languages', 'Syntax and concepts of various programming languages', 'semantic', 'codebert-base',
        3209849, '武星', '武星');