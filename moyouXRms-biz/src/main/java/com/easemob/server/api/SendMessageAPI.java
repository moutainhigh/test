package com.easemob.server.api;

import com.easemob.server.comm.body.AudioMessageBody;
import com.easemob.server.comm.body.CommandMessageBody;
import com.easemob.server.comm.body.ImgMessageBody;
import com.easemob.server.comm.body.MessageBody;
import com.easemob.server.comm.body.TextMessageBody;
import com.easemob.server.comm.body.VideoMessageBody;

/**
 * This interface is created for RestAPI of Sending Message, it should be
 * synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:
 *      50messages
 */
public interface SendMessageAPI {
	/**
	 * 发送消息 <br>
	 * POST
	 * 
	 * @param payload
	 *            消息体
	 * @return
	 * @see MessageBody
	 * @see TextMessageBody
	 * @see ImgMessageBody
	 * @see AudioMessageBody
	 * @see VideoMessageBody
	 * @see CommandMessageBody
	 */
	Object sendMessage(Object payload);
}
