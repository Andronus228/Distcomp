package com.example.eger.dc.lab5.module

import com.datastax.oss.driver.api.core.CqlSession
import com.example.eger.dc.lab5.dao.MessageDao
import com.example.eger.dc.lab5.dao.impl.MessageDaoImpl
import com.example.eger.dc.lab5.repository.MessagesRepository
import com.example.eger.dc.lab5.repository.impl.MessagesRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val messagesRepositoryQualifier: StringQualifier = StringQualifier("messages_repository")

val dataModule: Module = module {
	single<MessageDao> {
		val cluster: CqlSession = get()

		MessageDaoImpl(cluster)
	}

	single<MessagesRepository>(messagesRepositoryQualifier) {
		val dao = get<MessageDao>()

		MessagesRepositoryImpl(dao)
	}
}