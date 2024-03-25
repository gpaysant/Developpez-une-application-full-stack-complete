INSERT INTO users (username, email, password) VALUES
    ('johndoe', 'johndoe@gmail.com', 'mdlsqkjfsqdmlojf=àfçdusfjdsàçfudsàqi'),
    ('janedie', 'janedie@gmail.com', 'dsjfsà=dçifçjfàéjçàçfjçjéàfjéàeçfà^dsjfàds');

INSERT INTO topics (title, description) VALUES
    ('angular', 'All informations about Angular'),
    ('html', 'Tips for HTML and so more');

SELECT @id_topic_angular := id FROM topics WHERE title = 'angular';
SELECT @id_topic_john := id FROM users WHERE email = 'johndoe@gmail.com';
SELECT @id_topic_jane := id FROM users WHERE email = 'janedie@gmail.com';

INSERT INTO subscriptions (topic_id, user_id) VALUES
    ((SELECT @id_topic_angular), (SELECT @id_topic_john)),
    ((SELECT @id_topic_angular), (SELECT @id_topic_jane));

INSERT INTO posts (title, date_creation, content, topic_id, user_id) VALUES
    ('last version angular', '2024-03-19', 'This topic provides information about updating your Angular applications to Angular version 17.The new deferrable views allow you to lazily load all the components, directives, and pipes in a a section of your template', (SELECT @id_topic_angular), (SELECT @id_topic_john)),
    ('New lifecycle hook', '2024-03-20', 'To improve the performance of Angular’s SSR and SSG, in the long-term we’d like to move away from DOM emulation and direct DOM manipulations. At the same time, throughout most applications’ lifecycle they need to interact with elements to instantiate third-party libraries, measure element size, etc.', (SELECT @id_topic_angular), (SELECT @id_topic_jane));

SELECT @id_post_version_angular := id FROM posts WHERE title = 'last version angular';
SELECT @id_post_lifecycle_angular := id FROM posts WHERE title = 'New lifecycle hook';

INSERT INTO comments (text, post_id, user_id) VALUES
    ('i love the latest version of angular', (SELECT @id_post_version_angular), (SELECT @id_topic_john)),
    ('Very interesting', (SELECT @id_post_lifecycle_angular), (SELECT @id_topic_jane));