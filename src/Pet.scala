/**
  * Created by Alberto on 20/12/2015.
  */
abstract class State
  case class Dead(causeOfDeath : String) extends State
  case class Alive(Awake: Boolean, span: Int) extends State

case class Pet (name: String, age: Int, hunger: Int, poop: Int, happiness: Int)

object PetActions {

  def createPet(name: String): (Pet, State) = (Pet(name, 0, 0, 0, 50), Alive(true, 0))

  def ageIncrement(age: Int) = age + 1

  def hungerIncrementAwake(hunger: Int) = hunger + 2

  def hungerIncrementAsleep(hunger: Int) = hunger + 1

  def feed(hunger: Int, happiness: Int) = {
    if (happiness > 20 && happiness < 100)
        (4, 2, 4) // (hungerDecrement, poopIncrement, happinessIncrement)
    else
      (0, 0, 0)
  }

  def increaseHappiness(x: Int) = {
    val y = x + 2
    if (y < 100) y else x
  }

  def clockTrick(pet: Pet, state: State): (Pet, State) = (pet, state) match {
    case (_, Dead(_)) => (pet, state)
    case (Pet(name, age, hunger, poop, happiness), Alive(_, _)) =>
      if (age >= 100) (pet, Dead(s":( $name died of old age"))
      else if (hunger >= 100) (pet, Dead(s":( $name died of hunger"))
      else
        state match {
          case Alive(true, span) => (new Pet(name, ageIncrement(age), hungerIncrementAwake(hunger), poop, happiness - 1), Alive(true, span))
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
    case (Pet(name, age, hunger, poop, happiness), Alive(_, _)) =>
      val (hungerDecrement, poopIncrement, happinessIncrement) = feed(hunger, happiness)
       val hunger1 = if (hunger - hungerDecrement < 0) hunger else  hunger - hungerDecrement
      (new Pet(name, age, hunger1, poop + poopIncrement, happiness + happinessIncrement), state)
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

  def cleanPet(pet: Pet, state: State): (Pet, State) = (pet, state) match {
    case (_, Dead(_)) => (pet, state)
    case (pet, _) => (new Pet(pet.name, pet.age, pet.hunger, pet.poop - 1, pet.happiness), Alive(true, 0))
  }
}

