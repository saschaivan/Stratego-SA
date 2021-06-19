package de.htwg.se.stratego.model

import com.google.inject.AbstractModule
import de.htwg.se.stratego.model.databaseComponent.FileIODatabaseInterface
import de.htwg.se.stratego.model.databaseComponent.fileIOMongo.FileIOMongo
import de.htwg.se.stratego.model.fileIoComponent.{FileIOInterface, fileIoJsonImpl}
import net.codingwell.scalaguice.ScalaModule

class FileIOModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

    bind[FileIODatabaseInterface].to[FileIOMongo]
    //bind[FileIODatabaseInterface].to[FileIOSlick]

    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
    //bind[FileIOInterface].to[fileIoXmlImpl.FileIO]

  }
}
