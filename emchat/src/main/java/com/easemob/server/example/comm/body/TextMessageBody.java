package com.easemob.server.example.comm.body;

import com.easemob.server.example.comm.constant.MsgType;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class TextMessageBody extends MessageBody {
	private String msg;

	public TextMessageBody(String targetType, String[] targets, String from,
			Map<String, String> ext, String msg) {
		super(targetType, targets, from, ext);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public ContainerNode<?> getBody() {
		if (!isInit()) {
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			node.put("type", MsgType.TEXT);
			node.put("msg", msg);
			this.getMsgBody().put("msg", node);
			this.setInit(true);
		}

		return this.getMsgBody();
	}

	public Boolean validate() {
		return super.validate() && StringUtils.isNotBlank(msg);
	}
}
