package io.pragra.learning.webclientdemo.entity;

import lombok.Data;

@Data
public class GitUser {
    private String name;
    private String login;
    private Long id;
    private String node_id;
    private String avatar_url;
    private Long gravatar_id;
    private String url;
    private String html_url;
    private String received_events_url;
    private String type;
    private String user_view_type;
    private Boolean site_admin;
}
