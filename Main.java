import java.util.ArrayList;
import java.util.List;

// Абстрактний базовий клас
abstract class Organism {
    protected String name;
    protected int age;

    public Organism(String name) {
        this.name = name;
        this.age = 0;
    }

    public abstract void liveOneDay();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    protected void growOlder() {
        age++;
    }
}

// Клас для рослин
class Plant extends Organism {
    private int growthLevel;

    public Plant(String name) {
        super(name);
        this.growthLevel = 0;
    }

    @Override
    public void liveOneDay() {
        growOlder();
        growthLevel++;
        System.out.println(name + " has grown to level " + growthLevel);
    }
}

// Клас для травоїдних
class Herbivore extends Organism {
    private int energy;

    public Herbivore(String name) {
        super(name);
        this.energy = 10;
    }

    @Override
    public void liveOneDay() {
        growOlder();
        energy--;
        System.out.println(name + " is grazing. Energy: " + energy);
    }

    public void eatPlant(Plant plant) {
        energy += 5;
        System.out.println(name + " eats " + plant.getName() + " for energy.");
    }
}

// Клас для хижаків
class Predator extends Organism {
    private int hunger;

    public Predator(String name) {
        super(name);
        this.hunger = 5;
    }

    @Override
    public void liveOneDay() {
        growOlder();
        hunger++;
        System.out.println(name + " is hunting. Hunger: " + hunger);
    }

    public void huntHerbivore(Herbivore herbivore) {
        hunger -= 3;
        System.out.println(name + " hunts " + herbivore.getName() + " and reduces hunger.");
    }
}

// Клас для управління екосистемою
class Ecosystem {
    private List<Organism> organisms;

    public Ecosystem() {
        organisms = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        organisms.add(organism);
    }

    public void simulateDay() {
        for (Organism organism : organisms) {
            organism.liveOneDay();
        }
    }
}

// Точка входу
public class Main {
    public static void main(String[] args) {
        Ecosystem ecosystem = new Ecosystem();

        // Створення організмів
        Plant grass = new Plant("Grass");
        Herbivore rabbit = new Herbivore("Rabbit");
        Predator fox = new Predator("Fox");

        // Додавання організмів до екосистеми
        ecosystem.addOrganism(grass);
        ecosystem.addOrganism(rabbit);
        ecosystem.addOrganism(fox);

        // Симуляція на 5 днів
        for (int day = 1; day <= 5; day++) {
            System.out.println("=== Day " + day + " ===");
            ecosystem.simulateDay();
            System.out.println();
        }

        // Взаємодії
        rabbit.eatPlant(grass);
        fox.huntHerbivore(rabbit);
    }
}
