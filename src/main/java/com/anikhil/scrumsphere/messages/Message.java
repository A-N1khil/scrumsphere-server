package com.anikhil.scrumsphere.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Message {

	private String messageId;
	private String content;
	private final String author;
	private Integer upVotes = 0;
}
