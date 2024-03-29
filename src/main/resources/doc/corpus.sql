DROP TABLE IF EXISTS chunk;
DROP TABLE IF EXISTS document;
DROP TABLE IF EXISTS corpus;
DROP TABLE IF EXISTS directory_structure;

CREATE TABLE corpus
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,                                        -- 语料库ID
    name        VARCHAR(64)  NOT NULL,                                                       -- 语料库名称
    corpus_desc VARCHAR(255),                                                                -- 语料库描述
    create_by   VARCHAR(8)   NOT NULL,                                                       -- 创建者ID
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_by   VARCHAR(8)   NOT NULL,                                                       -- 修改者ID
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 修改时间
    PRIMARY KEY (id)
) AUTO_INCREMENT = 3209847;

CREATE TABLE document
(
    id           INT UNSIGNED     NOT NULL AUTO_INCREMENT,                                        -- 文档ID
    corpus_id    INT UNSIGNED     NOT NULL,                                                       -- 语料库ID
    name         VARCHAR(64)      NOT NULL,                                                       -- 文档名称
    type         TINYINT UNSIGNED NOT NULL,                                                       -- 文档类型
    path         VARCHAR(255)     NOT NULL,                                                       -- 文档路径
    chunk_size   INT UNSIGNED     NOT NULL DEFAULT 0,                                             -- 分片数量
    vector_state TINYINT UNSIGNED NOT NULL DEFAULT 0,                                             -- 向量化状态
    create_by    VARCHAR(8)       NOT NULL,                                                       -- 创建者ID
    create_time  DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_by    VARCHAR(8)       NOT NULL,                                                       -- 修改者ID
    update_time  DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 修改时间
    PRIMARY KEY (id),
    FOREIGN KEY (corpus_id) REFERENCES corpus (id)
) AUTO_INCREMENT = 3209847;

CREATE TABLE chunk
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,                                        -- 分片ID
    corpus_id   INT UNSIGNED NOT NULL,                                                       -- 语料库ID
    doc_id      INT UNSIGNED NOT NULL,                                                       -- 文档ID
    metadata    VARCHAR(255),                                                                -- 元数据
    content     VARCHAR(1024),                                                               -- 分片内容
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 修改时间
    PRIMARY KEY (id),
    FOREIGN KEY (corpus_id) REFERENCES corpus (id),
    FOREIGN KEY (doc_id) REFERENCES document (id)
) AUTO_INCREMENT = 3209847;

CREATE TABLE directory_structure
(
    id        INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    parent_id INT UNSIGNED     NOT NULL, -- 上一级目录项ID
    name      VARCHAR(64)      NOT NULL, -- 目录项的名称
    type      TINYINT UNSIGNED NOT NULL, -- 目录项的类型, 0: root, 1: corpus, 2: document, 3: chunk
    actual_id INT UNSIGNED     NOT NULL, -- 指向实际数据表中的ID（例如，语料库ID、文档ID或分片ID）
    PRIMARY KEY (id),
    INDEX idx_parent_id (parent_id),
    INDEX idx_actual_id (actual_id)
);

INSERT INTO directory_structure(id, parent_id, name, type, actual_id)
VALUES (1, 1, '根目录', 0, 0);

INSERT INTO corpus(name, corpus_desc, create_by, update_by)
VALUES ('中船规范', '', '武星', '武星'),
       ('语料库A', '语料库A介绍', '武星', '武星'),
       ('语料库B', '语料库B介绍', '武星', '武星');

INSERT INTO document(corpus_id, name, type, path, chunk_size, vector_state, create_by, update_by)
VALUES (3209847, '钢质海船入级规范2023-1.pdf', 1, '48431.pdf', 3, 0, '武星', '武星'),
       (3209847, '钢质海船入级规范2023-2.pdf', 1, '48432.pdf', 2, 0, '武星', '武星'),
       (3209847, '钢质海船入级规范2023-3.pdf', 1, '48433.pdf', 1, 0, '武星', '武星'),
       (3209848, '文档1.pdf', 1, '1321.pdf', 2, 0, '武星', '武星'),
       (3209848, '文档2.pdf', 1, '1322.pdf', 1, 0, '武星', '武星'),
       (3209849, '文档3.pdf', 1, '1322.pdf', 3, 0, '武星', '武星');

INSERT INTO chunk(corpus_id, doc_id, content)
VALUES (3209847, 3209847,
        'CCS 将授予入级符号与相应的附加标志，并签发入级证书。\n\n2.9.1.2 船东应进行维修保养，并按入级证书规定的条件进行营运。 2.9.1.3 已经授予 CCS 船级的船舶，按照本规范进行建造后检验并符合本规范的要求时，船级\n\n继续有效，CCS 将签署或换发新的入级证书。\n\n2.9.1.4 当 CCS 有合理理由认为船舶在两次定期检验期间存在影响船级保持可能性时，CCS 保 留对船舶进行不定期检'),
       (3209847, 3209847,
        '授予相应的入级符号与附加标志。\n\n2.3.1.3 凡船舶的船体(包括设备)与轮机(包括电气设备)经 CCS 批准入级，将根据不同情况授予\n\n下列入级符号：\n\n★ CSA ★ CSM 或 ★ CSA ★ CSM 或 ★ CSA ★ CSM\n\n入级符号含义如下： ★ CSA——表示船舶的结构与设备由 CCS 审图和建造中检验，并符合 CCS 规范的规定。特殊 情况下，船舶在临近交船前，其结构和设备经'),
       (3209847, 3209847,
        '(3) 如下情况之一，将导致船级处于暂停程序，除非验船师为完成这些检验已登轮： ① 如 CCS 给出的船舶船级条件在规定时间(该时间将通知船东)内未消除，且未经 CCS 同意展期； ② 如在年度检验时，到期或过期的循环检验项目未完成，且未经 CCS 同意展期； ③ 如除年度检验、中间检验或特别检验以外其他建造后检验，未在到期日完成，且未经 CCS\n\n同意展期；\n\n④ 如任何损坏、缺陷、故障或搁浅的'),
       (3209847, 3209848,
        '★ CSM——表示船舶轮机和电气设备不是由 CCS 审图和建造中检验，其后经 CCS 进行入级检\n\n验，认为其符合 CCS 规范的规定。\n\n2.3.2 附加标志\n\n1-6\n\n2.3.2.1 附加标志是船舶不同特点的分级表述，加注在入级符号之后。可分为必需性和可选性附加标志。 2.3.2.2 对可选性附加标志，应由船东申请，经 CCS 审图与检验，确认符合 CCS 规范的相应规\n\n定后，由 CCS '),
       (3209847, 3209848,
        '特别检验展期：\n\n(a) 年度检验； (b) 对船级条件重新进行检查； (c) 特别检验的项目尽实际可能地进行； (d) 如果在船级展期的到期日之前，坞内检验已到期，则应由认可的水下检验公司进行一次水下 检查。如船舶的水下部分没有船级条件，且展期后的坞检到期日距上次坞检不超过 36 个月，则可不 必进行水下检查。\n\nb. 如船舶的入级证书预计在海上航行时将过期，且在证书到期之前船东已向 CCS 书'),
       (3209847, 3209849,
        '④ 当确认船舶在其检验到期之前，验船师业已登船，但在相应过期检验满意完成之前投入营运\n\n时。\n\n(5) 如由于出现超出船东或 CCS 正常控制能力的不可抗力的情况，导致船舶不在能够及时完成 过期检验项目的港口，经船东申请，在满足下述条件下，CCS 可同意船舶在保持船级情况下，直接 航行到卸货港卸货，必要时，随后压载航行至将完成检验的港口：\n\n① 检查船舶记录； ② 当因不可预见的原因导致 CCS'),
       (3209848, 3209850,
        '的检验单位审核并满意后，由 CCS 总裁或其授权人员签发新的入级证书。\n\n2.10.3.5 尽管有 2.10.2.1 规定，CCS 在执行 2.10.3.4 条时，可基于综合考虑所获得的该船舶有关 其他安全营运的资料/信息,诸如船旗国/港口国安全检查信息、船公司安全管理状况等，决定小于 5 年 的入级证书有效期和/或采取其他必要的限制措施，如在签发新的入级证书时加注航行限制条件等。 如缩短的入级证'),
       (3209848, 3209850,
        '2.11.2.2 随后，若认可船用产品的增加或性能变更，CCS 将及时更新船用产品录。\n\n第 12 节 信息提供与保密\n\n2.12.1 信息提供 2.12.1.1 信息的提供方，应对向 CCS 提供船舶入级所需信息的真实性、及时性和完整性负责。\n\n2.12.2 信息披露 2.12.2.1 除下列情况外，CCS 不会将入级得到的信息，披露给合同和表 2.12.2.2（1）及表 2.12.2.2\n\n（'),
       (3209848, 3209851,
        '2.4.1.3 申请人应提供从事上述服务所需的图纸和技术文件。 2.4.1.4 为保障 CCS 验船师职业健康安全，CCS 已建立职业健康安全管理体系。申请人申请 CCS 入级和法定检验服务意味着尊重 CCS 职业健康安全管理体系，并承诺为进入与申请的检验服务相关 的设施的 CCS 验船师提供符合验船师国籍所在国、检验机构所在地国家规定和/或检验现场所在地主\n\n1-7\n\n管当局规定的安全技术要求或'),
       (3209849, 3209852,
        '表 2.12.2.2（1）\n\n相关各方可获得的信息\n\n信 息 类 别\n\n船东\n\n船旗国\n\n港口国\n\n保险公司*\n\n船厂\n\n1.CCS 的常规文件 规范、指南(船级和法定要求) 验船师须知\n\n1\n\n1\n\n1\n\n1\n\n1\n\n1\n\n质量手册\n\n1\n\n1\n\n1\n\n1\n\n1\n\n船舶录\n\n1\n\n1\n\n1\n\n1\n\n1\n\n2.与船舶有关的信息 A.新造船\n\n① CCS 船舶录及产品录可在 CCS 网站 https'),
       (3209849, 3209852,
        '7\n\n7**\n\n1\n\n1\n\n7\n\n1\n\n1\n\n5\n\n7\n\n1\n\n1\n\n1\n\n3\n\n3\n\n3\n\n7\n\n—法定检验到期日期 —法定证书到期日期 —登记的法定条件项目 —过期的法定条件项目 3.其他信息 同船厂和/或船东信函文件 CCS 的质量体系审核 转级报告\n\n7\n\n7**\n\n1\n\n1\n\n7\n\n7**\n\n1\n\n1\n\n7\n\n7**\n\n1\n\n5***\n\n1***\n\n1\n\n7**\n\n7\n\n6\n\n6\n\n5&'),
       (3209849, 3209852,
        '1\n\n1\n\n1\n\n质量手册\n\n1\n\n1\n\n1\n\n1\n\n1\n\n船舶录\n\n1\n\n1\n\n1\n\n1\n\n1\n\n① 适用于满足 SOLAS 第 II-1/3-10 条(散货船和油船目标型船舶建造标准)要求的油船和散货船。\n\n1-15\n\n相关各方可获得的信息\n\n信 息 类 别\n\n船东\n\n船旗国\n\n港口国\n\n保险公司*\n\n船厂\n\n2.与船舶有关的信息 A.新造船 批准的图纸\n\n1\n\n1\n\n7\n\n正式批准函\n\n1\n');

INSERT INTO directory_structure(parent_id, name, type, actual_id)
VALUES (1, '中船规范', 1, 3209847),
       (1, '语料库A', 1, 3209848),
       (1, '语料库B', 1, 3209849),
       (3209847, '钢质海船入级规范2023-1.pdf', 2, 3209847),
       (3209847, '钢质海船入级规范2023-2.pdf', 2, 3209848),
       (3209847, '钢质海船入级规范2023-3.pdf', 2, 3209849),
       (3209848, '文档1.pdf', 2, 3209850),
       (3209848, '文档2.pdf', 2, 3209851),
       (3209849, '文档3.pdf', 2, 3209852),
       (3209847, '分片-3209847', 3, 3209847),
       (3209847, '分片-3209848', 3, 3209848),
       (3209847, '分片-3209849', 3, 3209849),
       (3209848, '分片-3209850', 3, 3209850),
       (3209848, '分片-3209851', 3, 3209851),
       (3209849, '分片-3209852', 3, 3209852),
       (3209850, '分片-3209853', 3, 3209853),
       (3209850, '分片-3209854', 3, 3209854),
       (3209851, '分片-3209855', 3, 3209855),
       (3209852, '分片-3209856', 3, 3209856),
       (3209852, '分片-3209857', 3, 3209857),
       (3209852, '分片-3209858', 3, 3209858);