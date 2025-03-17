@startuml
 Game "1" *-- "1" Board : contains
 Game "1" *-- "*" Character : manages
 Game "1" *-- "1" TurnManager : uses
 Board "1" *-- "*" HexTile : contains
 Character <|-- PlayerCharacter
 Character <|-- EnemyCharacter
 Character "1" *-- "*" Ability : has
 Character "1" *-- "*" Item : inventory
 GameUI "1" --> "1" Game
 GameUI "1" --> "1" BoardPane
 BoardPane "1" *-- "1" Board
 Weapon --|> Item
 AttackAction ..|> Action
 AbilityAction ..|> Action
 MoveAction ..|> Action
 Item<|--Consumable
 Character"1"-->"1" Weapon: equipamiento
 HexTile"1"-->"0..1" Character:occupant
 Character"1"*--"1"Position
 TurnManager"1"*--"*"Character: turnOrder
 MoveAction --> Position
 AttackAction --> Character
 AbilityAction --> Ability
 Event "1" --> "1" Effect : effect

class Game {
    -Board board
    -TurnManager turnManager
    -List<Character> characters
    -GameState currentState
    -GameUI gameUI
    +Game()
    +startGame() : void
    +runGameLoop() : void
    +update() : void
    +setGameUI(gameUI: GameUI) : void
    +getBoard() : Board
    +getCharacters() : List<Character>
}

class Board {
    -int rows
    -int cols
    -HexTile[][] tiles
    +Board(rows: int, cols: int)
    -initializeTiles() : void
    +getTile(row: int, col: int) : HexTile
    +getNeighbors(tile: HexTile) : List<HexTile>
    +getTiles() : HexTile[][]
}

class HexTile {
    -int row
    -int col
    -TerrainType terrain
    -Character occupant
    +HexTile(row: int, col: int, terrain: TerrainType)
    +getRow() : int
    +getCol() : int
    +getTerrain() : TerrainType
    +isOccupied() : boolean
    +getOccupant() : Character
    +setOccupant(occupant: Character) : void
}

class Position {
    -int row
    -int col
    +Position(row: int, col: int)
    +getRow() : int
    +getCol() : int
    +distanceTo(other: Position) : int
}

class TurnManager {
    -List<Character> turnOrder
    -int currentTurnIndex
    +TurnManager(turnOrder: List<Character>)
    +getCurrentCharacter() : Character
    +nextTurn() : void
    +resetTurn() : void
}

abstract class Character {
    -String name
    -int maxHealth
    -int currentHealth
    -int attack
    -int defense
    -int movement
    -Position position
    -Role role
    -List<Ability> abilities
    -List<Item> inventory
    -Weapon equippedWeapon
    +Character(name: String, health: int, attack: int, defense: int, movement: int, position: Position, role: Role)
    +move(newPosition: Position) : void
    +attack(target: Character) : void
    +receiveDamage(damage: int) : void
    +useAbility(ability: Ability, target: Character) : void
    +addItem(item: Item) : void
    +equipWeapon(weapon: Weapon) : void
    +isAlive() : boolean
    +performAction(action: Action) : void
    +getCurrentHealth() : int
    +getMaxHealth() : int
    +setCurrentHealth(health: int) : void
    +getName() : String
}

class PlayerCharacter {
    +PlayerCharacter(name: String, health: int, attack: int, defense: int, movement: int, position: Position, role: Role)
    +performAction(action: Action) : void
}

class EnemyCharacter {
    +EnemyCharacter(name: String, health: int, attack: int, defense: int, movement: int, position: Position, role: Role)
    +performAction(action: Action) : void
}

interface Action {
    +execute() : void
}

class MoveAction {
    -Character character
    -Position destination
    +MoveAction(character: Character, destination: Position)
    +execute() : void
}


class AttackAction {
    -Character attacker
    -Character target
    +AttackAction(attacker: Character, target: Character)
    +execute() : void
}

class AbilityAction {
    -Character user
    -Ability ability
    -Character target
    +AbilityAction(user: Character, ability: Ability, target: Character)
    +execute() : void
}


class Ability {
    -String name
    -String description
    -int cooldown
    -int currentCooldown
    +Ability(name: String, description: String, cooldown: int)
    +execute(user: Character, target: Character) : void
    +canUse() : boolean
    +updateCooldown() : void
    +getName() : String
    +getDescription() : String
}

interface Effect {
    +apply(board: Board) : void
}

class Event {
    -String eventName
    -int duration
    -Effect effect
    +Event(eventName: String, duration: int, effect: Effect)
    +applyEvent(board: Board) : void
    +update() : void
    +isActive() : boolean
}

abstract class Item {
    -String id
    -String name
    -String description
    +Item(id: String, name: String, description: String)
    +getId() : String
    +getName() : String
    +getDescription() : String
    +use(user: Character) : void
}

class Consumable {
    -int healthRestored
    +Consumable(id: String, name: String, description: String, healthRestored: int)
    +getHealthRestored() : int
    +use(user: Character) : void
}

class Weapon {
    -int damage
    -double attackSpeed
    -WeaponType type
    +Weapon(id: String, name: String, description: String, damage: int, attackSpeed: double, type: WeaponType)
    +getDamage() : int
    +getAttackSpeed() : double
    +getType() : WeaponType
    +use(user: Character) : void
}

class GameUI {
    -Game game
    -BoardPane boardPane
    +start(primaryStage: Stage) : void
    +updateUI() : void
    +main(args: String[]) : void
}

class BoardPane {
    -Board board
    -Canvas canvas
    -int tileSize
    +BoardPane(board: Board)
    +render() : void
}

@enduml
