DROP TABLE IF EXISTS model;

CREATE TABLE model
(
    id               INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    uid              VARCHAR(64)   NOT NULL,
    type             VARCHAR(16)   NOT NULL,                                                       -- 模型类型
    name             VARCHAR(16)   NOT NULL,                                                       -- 模型名称
    lang             VARCHAR(16)   NOT NULL,                                                       -- 模型语言
    ability          VARCHAR(32)   NOT NULL,                                                       -- 模型能力
    model_desc       VARCHAR(255),                                                                 -- 模型描述
    format           VARCHAR(16)   NOT NULL,                                                       -- 模型格式
    size_in_billions DECIMAL(5, 2) NOT NULL,                                                       -- 模型参数大小
    quantization     VARCHAR(255),                                                                 -- 模型量化信息
    revision         VARCHAR(255),                                                                 -- 模型版本
    context_length   INT UNSIGNED  NOT NULL,                                                       -- 模型可以容纳的最大文本长度
    create_by        VARCHAR(8)    NOT NULL,                                                       -- 创建者
    create_time      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_by        VARCHAR(8)    NOT NULL,                                                       -- 修改者
    update_time      DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 修改时间
    PRIMARY KEY (id)
) AUTO_INCREMENT = 3209847;

INSERT INTO model (uid, type, name, lang, ability, model_desc, format, size_in_billions, quantization, revision,
                   context_length, create_by, update_by)
VALUES ('model_001', 'NLP', 'ChatGPT', 'English', 'Text Generation', 'Generates human-like text based on prompts.',
        'Transformer', 175.00, 'FP16', 'rev_1.0', 2048, '武星', '武星'),
       ('model_002', 'CV', 'VisionGPT', 'Multi', 'Image Recognition', 'Identifies objects and themes in images.', 'CNN',
        60.00, 'INT8', 'rev_1.2', 512, '武星', '武星'),
       ('model_003', 'NLP', 'TranslateGPT', 'Multi', 'Translation', 'Translates text across multiple languages.',
        'Transformer', 110.00, NULL, 'rev_2.1', 1024, '武星', '武星'),
       ('model_004', 'Audio', 'SoundGPT', 'Multi', 'Audio Processing',
        'Processes and generates audio, including speech.', 'RNN', 85.00, 'FP32', 'rev_1.0', 256, '武星', '武星'),
       ('model_005', 'NLP+CV', 'MultiModalGPT', 'English', 'Multi-Modal', 'Generates text from image inputs.',
        'Transformer+CNN', 200.00, 'FP16', 'rev_3.0', 2048, '武星', '武星');