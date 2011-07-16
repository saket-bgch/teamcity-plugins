package com.goldin.plugins.teamcity.messenger.api

import org.gcontracts.annotations.Ensures
import org.gcontracts.annotations.Requires

/**
 * Messages storage
 */
interface MessagesTable
{

    @Requires({ message && ( message.id < 0 ) })
    @Ensures({ ( result.id > 0 ) && ( result.timestamp == message.timestamp ) })
    Message addMessage( Message message )


    @Requires({ messageId > 0 })
    @Ensures({ result.id == messageId })
    Message getMessage( long messageId )


    @Requires({ ( messageIds != null ) && messageIds.every { it > 0 } })
    @Ensures({ messageIds.every { ! containsMessage( it ) } && result.every { ! containsMessage( it.id ) } })
    List<Message> deleteMessage( long ... messageIds )


    @Requires({ ( messageId > 0 ) && username })
    @Ensures({ ( result == null ) || ( result.id == messageId ) })
    Message deleteMessageByUser ( long messageId, String username )


    @Ensures({ result != null })
    List<Message> getAllMessages()


    @Requires({ messageId > 0 })
    boolean containsMessage( long messageId )


    @Ensures({ result > -1 })
    int getNumberOfMessages()


    void deleteAllMessages()


    @Ensures({ result })
    Map getPersistencyData()


    @Requires({ data })
    void setPersistencyData ( Map data )
}
