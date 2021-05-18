package de.htwg.se.stratego.model.fileIODatabase

import com.google.inject.AbstractModule
import de.htwg.se.stratego.model.fileIODatabase.fileIOSlick.FileIOSlick
import de.htwg.se.stratego.model.fileIODatabase.fileIOMongo.FileIOMongo
import net.codingwell.scalaguice.ScalaModule

class FileIOModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

    //bind[FileIODatabaseInterface].to[FileIOMongo]
    bind[FileIODatabaseInterface].to[FileIOSlick]

  }
}
