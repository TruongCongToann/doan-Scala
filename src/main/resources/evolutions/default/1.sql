# # --- !Ups
#
# create table posts
# (
#     id          BIGINT auto_increment,
#     content     varchar(300) not null,
#     title       varchar(100) not null,
#     thumbnail   Longblob not null,
#     author_id   bigint       not null,
#     created_at  datetime     not null,
#     updated_at  datetime     not null,
#     constraint posts_pk
#         primary key (id)
# );
#
# # --- !Downs
# DROP TABLE posts;