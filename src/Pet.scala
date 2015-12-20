/**
  * Created by Alberto on 20/12/2015.
  */
abstract class State
  case class Dead(causeOfDeath : String) extends State
  case class Alive(Awake: Boolean, span: Int) extends State

case class Pet (name: String, age: Int, hunger: Int, poop: Int, happiness: Int)

object PetActions {

  def createPet(name : String ) = (Pet (name, 0, 0, 0, 100), Alive(true, 0))

  def ageIncrement(age: Int) = age + 1

  def hungerIncrementAwake(hunger: Int) = hunger + 2

  def hungerIncrementAsleep(hunger: Int) = hunger + 1

  def feed(hunger: Int, happiness: Int) = if (happiness < 20) (2, 1, 2) else (0, 0, 0) // (hungerDecrement, poopIncrement, happinessIncrement)

  def increaseHappiness(x: Int) = x + 2

  def clockTrick(pet: Pet, state: State): (Pet, State) = (pet, state) match {
    case (_, Dead(_)) => (pet, state)
    case (Pet(name, age, hunger, poop, happiness), Alive(_, _)) =>
      if (age >= 100) (pet, Dead(s":( $name died of old age"))
      else if (hunger >= 100) (pet, Dead(s":( $name died of hunger"))
      else
        state match {
          case Alive(true, span) => (new Pet(name, ageIncrement(age), hungerIncrementAwake(hunger), poop, happiness), Alive(true, span))
          case Alive(false, span) =>
            val newSpan = span + 1
            if (newSpan >= 20000)
              (new Pet(name, ageIncrement(age), hungerIncrementAsleep(hunger), poop, happiness), Alive(true, 0))
            else
              (new Pet(name, ageIncrement(age), hungerIncrementAsleep(hunger), poop, happiness), Alive(false, newSpan))
        }
  }

  def feedPet(pet: Pet, state: State): (Pet, State) = (pet, state) match {
    case (_, Dead(_)) => (pet, state)
    case (Pet(name, age, hunger, poop, happiness), state) =>
      val (hungerDecrement, poopIncrement, happinessIncrement) = feed(hunger, happiness)
      (new Pet(name, age, hunger + hungerDecrement, poop + poopIncrement, happiness + happinessIncrement), state)
  }

  def playWithPet(pet: Pet, state: State): (Pet, State) = (pet, state) match {
    case (_, Dead(_)) => (pet, state)
    case (Pet(name, age, hunger, poop, happiness), Alive(true, _)) =>
      (new Pet(name, age, hunger, poop, increaseHappiness(happiness)), state)
  }

  def putToSleep(pet: Pet, state: State): (Pet, State) = (pet, state) match {
    case (_, Dead(_)) => (pet, state)
    case (pet, Alive(true, _)) => (pet, Alive(false, 0))
    case (pet, Alive(false, span)) => (pet, Alive(false, span))
  }
}

object EntryPoint {
  def main(args:Array[String]) = {
    println("Hello, please Enter a name for your pet")
    val name = scala.io.StdIn.readLine()
    val (pet, state) = PetActions.createPet(name)
    println(s"Hello friend my name is $name")

    var option = "";

    do{

      var petState = PetActions.clockTrick(pet, state)
      println( "Actions: Feed, Play, Sleep, Quit:")
      option = scala.io.StdIn.readLine()
      option.toLowerCase() match {
        case "feed" => petState = PetActions.feedPet(petState._1, petState._2)
        case "play" => petState = PetActions.playWithPet(petState._1, petState._2)
        case "sleep" => petState = PetActions.putToSleep(petState._1, petState._2)
        case _ =>    println( "Actions: Feed, Play, PutToSleep, Quit:")
      }
    } while( option.toLowerCase() != "quit" )

  }
}