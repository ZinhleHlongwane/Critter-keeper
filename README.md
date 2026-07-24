# 🐾 Critter Keeper

**A tiny multiplayer world where you adopt a creature, keep it happy, and explore a hazard-filled yard — one TCP connection at a time.**

Critter Keeper is a client-server simulation written in Java. Spin up the server, connect as many keepers as you like, and each one adopts a creature and roams a shared grid together — feeding, playing, resting, and dodging ponds and thorn bushes along the way.

---

## ✨ What makes it interesting

- **A real client-server game**, not a toy demo — a socket server juggling multiple concurrent keepers, speaking a JSON line protocol.
- **Five creatures, five personalities.** Every species is tuned differently: a Puppy burns through energy fast but bounces back quickly; a Turtle barely gets hungry and shrugs off almost everything; a Dragonling just doesn't care about thorn bushes.
- **A command pattern driving every action** — twelve commands (`ADOPT`, `FORWARD`, `LOOK`, `FEED`, and more), each a self-contained class, dispatched through a single factory.
- **A configurable world.** Drop hazards anywhere you like via a simple JSON file and reshape the yard without touching code.
- **103 tests.** Every layer — world physics, creature stats, hazards, commands, the protocol, config loading, and the full request pipeline — is covered.

---

## 🎮 Quick start

```bash
# Build the project
mvn package

# Start the shelter server (default port 5050, using shelter.json)
java -cp target/classes com.zinhle.critterkeeper.server.Server 5050 shelter.json

# In another terminal, connect as a keeper
java -cp target/classes com.zinhle.critterkeeper.client.Client Zinhle localhost 5050
```

Or use the bundled scripts once Maven has pulled your dependencies:

```bash
./server.sh
./client.sh Zinhle
```

---

## 🕹️ Commands

| Command | What it does |
|---|---|
| `ADOPT <species> <name>` | Adopt a creature — `puppy`, `kitten`, `dragonling`, `rabbit`, or `turtle` |
| `FORWARD [steps]` | Walk forward, default 1 step |
| `BACK [steps]` | Walk backward without turning around |
| `LEFT` / `RIGHT` | Turn 90 degrees |
| `FEED` | Reduce your creature's hunger |
| `PLAY` | Boost happiness (costs energy) |
| `REST` | Restore energy |
| `STATE` | Show your position, facing, and creature's stats |
| `LOOK` | See hazards and other keepers within 2 squares |
| `RELEASE` | Return your creature to the shelter |
| `QUIT` | Leave the shelter |
| `HELP` | List all commands |

---

## 🐕 The creatures

| Species | Personality |
|---|---|
| **Puppy** | Boundless energy — covers ground fast, needs feeding often |
| **Kitten** | Low maintenance — barely hungry, naps itself back to full |
| **Dragonling** | Fire-breather — shrugs off thorn bushes, but slow and hungry |
| **Rabbit** | Quick hopper — the only one that can skip clean over a mud puddle |
| **Turtle** | Thick shell — nearly immune to hazards, moves at its own pace |

---

## 🌍 The yard

The shelter is a grid you configure in `shelter.json`:

```json
{
  "width": 10,
  "height": 10,
  "hazards": [
    { "type": "pond", "x": 2, "y": 3 },
    { "type": "thornbush", "x": -2, "y": -3 },
    { "type": "mudpuddle", "x": 0, "y": 4 }
  ]
}
```

- **Pond** — fully blocks the way.
- **ThornBush** — passable, but costs happiness.
- **MudPuddle** — passable, small happiness cost, and Rabbits skip it entirely.

---

## 🏗️ Architecture

```
src/main/java/com/zinhle/critterkeeper/
├── world/       Direction, Position, Keeper, Shelter — the grid and everyone in it
├── creatures/   Creature (abstract) + 5 species + a factory
├── hazards/     Hazard (abstract) + Pond, ThornBush, MudPuddle
├── commands/    Command (abstract) + 12 commands + a factory
├── protocol/    Request, Response, State, JsonParser — the wire format
├── config/      Loads shelter.json into a live Shelter
├── server/      Server, ClientHandler, ShelterManager
└── client/      An interactive command-line client
```

Every design decision here mirrors classic OOP patterns worth knowing:
**abstract base classes with polymorphic subclasses** (creatures, hazards),
the **command pattern** (every player action), a **factory** for building
the right object from a string (species, commands, hazards), and a clean
separation between the **protocol** (what goes over the wire) and the
**domain model** (what the world actually is).

---

## 🧪 Testing

```bash
mvn test
```

103 tests across every package — world physics, each creature's stats,
each hazard's behaviour, each command in isolation, the JSON protocol,
config loading (including malformed and missing files), and full
end-to-end request flows through the server's dispatcher.

---

## 📦 Requirements

- Java 21
- Maven
- Dependencies: Gson, Picocli, JUnit 5, Mockito (all resolved automatically by Maven)

---

*A personal project exploring client-server architecture, the command pattern, and polymorphism in Java.*
