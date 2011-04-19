/*
 * Copyright 2005-2010 LAMP/EPFL
 */
// $Id$

package scala.tools.eclipse.util

import scala.tools.nsc.Settings

object IDESettings {
  import ScalaPluginSettings._
  case class Box(name: String, userSettings: List[Settings#Setting])
  def shownSettings(s : Settings) : List[Box] = {
    import s._

    List(
      Box("Standard",
        List(deprecation, g, optimise, target, unchecked,
             pluginOptions, nospecialization, verbose)),
      Box("Advanced",
    	List(checkInit, Xchecknull, elidebelow,
             Xexperimental, future, XlogImplicits,
             Xmigration28, noassertions, nouescape, plugin, disable,
             require, pluginsDir, Xwarnfatal)),
      Box("Private",
        List(Xcloselim, Xdce, inline, Xlinearizer, Ynogenericsig, noimports,
             selfInAnnots, Yrecursion, refinementMethodDispatch,
             Ywarndeadcode, Ybuildmanagerdebug)),
      Box("Presentation Compiler",
        List(YpresentationDebug, YpresentationVerbose, YpresentationLog, YpresentationReplay, YpresentationDelay)))
  }
  
  def pluginSettings: List[Box] = {
    List(Box("Scala Plugin Debugging", List(YPlugininfo)),
         Box("Sbt", List(pathToSbt, sbtJavaArgs)))
  }
  
  def buildManagerSettings: List[Box] = {
    List(Box("Build manager", List(buildManager)))    
  }
}

object ScalaPluginSettings extends Settings {
  val YPlugininfo = BooleanSetting("-plugininfo", "Enable logging of the Scala Plugin info")
  val buildManager = ChoiceSetting("-buildmanager", "which", "Build manager to use", List("refined"), "refined")
  val pathToSbt    = StringSetting("-pathToSbt", "path", "The full path to sbt-launch.jar", "/usr/bin/sbt-launch.jar")
  val sbtJavaArgs  = StringSetting("-sbtJavaArgs", "", "Additional JVM arguments", "-Xmx1000M")
}
