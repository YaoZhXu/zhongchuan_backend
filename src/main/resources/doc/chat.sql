DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username    VARCHAR(8)   NOT NULL,                                                       -- 用户名
    password    VARCHAR(32)  NOT NULL,                                                       -- 密码哈希值
    email       VARCHAR(100),                                                                -- 邮箱
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,                             -- 创建时间
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 修改时间
    PRIMARY KEY (id)
) AUTO_INCREMENT = 3209847;

CREATE TABLE topic
(
    id         INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL, -- 会话名称
    user_id    INT UNSIGNED NOT NULL, -- 创建人ID
    start_time DATETIME     NOT NULL, -- 会话开始时间
    end_time   DATETIME     NOT NULL, -- 会话结束时间
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) AUTO_INCREMENT = 65535;

CREATE TABLE chat
(
    id           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    topic_id     INT UNSIGNED NOT NULL, -- 会话ID
    user_id      INT UNSIGNED NOT NULL, -- 会话创建人ID
    sender_id    INT UNSIGNED,          -- 消息创建人ID
    role         VARCHAR(255) NOT NULL, -- 角色
    content      TEXT         NOT NULL, -- 消息内容
    send_time    DATETIME     NOT NULL, -- 消息发送时间
    source_links VARCHAR(1024),         -- 原文链接
    review       VARCHAR(255),          -- 评价
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topic (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    INDEX idx_topic_id (topic_id)
);

INSERT INTO user (username, password, email)
VALUES ('武星', '12345678', 'xingwu@shu.edu.cn'),
       ('张源', '12345678', 'zhangyuan@shu.edu.cn');

INSERT INTO topic (name, user_id, start_time, end_time)
VALUES ('AI和未来', 3209847, '2024-03-22 10:00:00', '2024-03-22 11:00:00'),
       ('最新科技趋势', 3209847, '2024-03-22 15:00:00', '2024-03-22 16:00:00'),
       ('宇宙探索的未来', 3209847, '2024-03-23 09:00:00', '2024-03-23 10:00:00'),
       ('全球气候变化对策', 3209848, '2024-03-23 14:00:00', '2024-03-23 15:00:00'),
       ('硕士学习的挑战与机遇', 3209848, '2024-03-24 08:00:00', '2024-03-24 09:00:00'),
       ('选择硕士专业的建议', 3209848, '2024-03-24 13:00:00', '2024-03-24 14:00:00');

INSERT INTO chat (topic_id, user_id, sender_id, role, content, send_time)
VALUES (65535, 3209847, 3209847, 'user',
        'AI将如何影响我们的未来?', '2024-03-22 10:05:00'),
       (65535, 3209847, null, 'assistant',
        'AI会在很多领域内改善我们的生活，比如医疗、教育和交通。', '2024-03-22 10:06:00'),
       (65535, 3209847, 3209847, 'user',
        '有哪些潜在的风险呢?', '2024-03-22 10:10:00'),
       (65535, 3209847, null, 'assistant',
        '潜在风险包括隐私泄露、失业增加和控制失衡等。', '2024-03-22 10:12:00');

INSERT INTO chat (topic_id, user_id, sender_id, role, content, send_time)
VALUES (65536, 3209847, 3209847, 'user',
        '今年有哪些值得关注的科技趋势?', '2024-03-22 15:05:00'),
       (65536, 3209847, null, 'assistant',
        '值得关注的趋势包括量子计算、人工智能的进步以及可持续能源技术。', '2024-03-22 15:06:00');

INSERT INTO chat (topic_id, user_id, sender_id, role, content, send_time)
VALUES (65537, 3209847, 3209847, 'user',
        '我们什么时候能在火星上建立第一个人类居住区?', '2024-03-23 09:05:00'),
       (65537, 3209847, null, 'assistant',
        '随着技术的发展，预计在未来20到30年内可能实现。', '2024-03-23 09:07:00'),
       (65537, 3209847, 3209847, 'user',
        '宇宙旅行对普通人开放还需要多久?', '2024-03-23 09:10:00'),
       (65537, 3209847, null, 'assistant',
        '随着商业宇宙旅行公司的进步，这可能在未来10年内成为现实。', '2024-03-23 09:12:00');

INSERT INTO chat (topic_id, user_id, sender_id, role, content, send_time)
VALUES (65538, 3209848, 3209847, 'user',
        '最有效的减少碳排放的方法是什么?', '2024-03-23 14:05:00'),
       (65538, 3209848, null, 'assistant',
        '提高能源效率、发展可再生能源和推行碳捕获技术是关键策略。', '2024-03-23 14:07:00'),
       (65538, 3209848, 3209847, 'user',
        '个人如何为对抗气候变化做出贡献?', '2024-03-23 14:10:00'),
       (65538, 3209848, null, 'assistant',
        '通过减少能源消耗、使用公共交通和支持环保政策，每个人都能发挥作用。', '2024-03-23 14:12:00');

INSERT INTO chat (topic_id, user_id, sender_id, role, content, send_time)
VALUES (65539, 3209848, 3209847, 'user',
        '硕士学习期间最大的挑战是什么?', '2024-03-24 08:05:00'),
       (65539, 3209848, null, 'assistant',
        '时间管理和研究方向的确定可能是两大挑战。', '2024-03-24 08:07:00'),
       (65539, 3209848, 3209847, 'user',
        '硕士期间有哪些不容错过的机遇?', '2024-03-24 08:15:00'),
       (65539, 3209848, null, 'assistant',
        '参与实际项目、国际交流和建立专业网络是宝贵的机遇。', '2024-03-24 08:17:00');

INSERT INTO chat (topic_id, user_id, sender_id, role, content, send_time)
VALUES (65540, 3209848, 3209847, 'user',
        '选择硕士专业时应该考虑哪些因素?', '2024-03-24 13:05:00'),
       (65540, 3209848, null, 'assistant',
        '未来职业目标、个人兴趣和专业在行业内的需求是关键考虑因素。', '2024-03-24 13:07:00'),
       (65540, 3209848, 3209847, 'user',
        '如果我对多个领域都感兴趣，应该怎么选择?', '2024-03-24 13:15:00'),
       (65540, 3209848, null, 'assistant',
        '可以考虑那些提供跨学科研究机会的专业，或是与导师讨论你的兴趣，找到最适合的方向。', '2024-03-24 13:17:00');
