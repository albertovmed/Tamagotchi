/**
  * Created by Alberto on 21/12/2015.
  */
object PetEntryPoint extends App{
    println("Hello, please Enter a name for your pet")
    val name = scala.io.StdIn.readLine()
    var petState = PetActions.createPet(name)
    println(s"Hello friend my name is $name")

    var option = ""

    do{

      petState = PetActions.clockTrick(petState._1, petState._2)
      println( "Actions: Feed, Play, Sleep, Clean, Quit:")
      option = scala.io.StdIn.readLine()
      option.toLowerCase() match {
        case "feed" => petState = PetActions.feedPet(petState._1, petState._2)
        case "play" => petState = PetActions.playWithPet(petState._1, petState._2)
        case "sleep" => petState = PetActions.putToSleep(petState._1, petState._2)
        case "clean" => petState = PetActions.cleanPet(petState._1, petState._2)
        case _ =>    println( "Actions: Feed, Play, PutToSleep, Clean, Quit:")
      }
      println(petState)
    } while( option.toLowerCase() != "quit" )
}