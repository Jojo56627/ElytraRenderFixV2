# ElytraRenderFix

With the minecraft:equippable component, it is possible to determine the layers that an item renders when it is equipped. This mod fixes a bug that occurs when rendering a chestplate layer and an elytra together. If the chestplate has a trim, the Elytra has a missingno texture because Minecraft's rendering code tries to apply the trim to the Elytra layer. This mod fixes this bug by simply preventing the trims from being rendered on the Elytra layer (wings). This ensures that the chestplate, trim, and Elytra are rendered correctly.

For example, the following JSON code is an equipment model (see [Minecraft Wiki](https://minecraft.wiki/w/Equipment)). This specifies that any item with the corrosponding asset_id in the item component minecraft:equippable (see [Minecraft Wiki](https://minecraft.wiki/w/Data_component_format#equippable)) should render normal diamond armor on the humanoid and humanoid_leggings layers (armor layers for helmet, chestplate, leggings, and boots) and, in addition, an elytra on the wings layer (using the player's cape if available).

diamond_armor_with_elytra.json:
```
{
  "layers": {
    "humanoid": [
      {
        "texture": "minecraft:diamond"
      }
    ],
    "humanoid_leggings": [
      {
        "texture": "minecraft:diamond"
      }
    ],
	"wings": [
      {
        "texture": "minecraft:elytra",
        "use_player_texture": true
      }
    ]
  }
}
```

minecraft:equippable component of the item (here a diamond chestplate):
```
{
  "slot": "chest",
  "asset_id": "mynamespace:diamond_armor_with_elytra"
}
```
If this item now has an armor trim, Minecraft attempts to render the trim over the normal armor layer (humanoid / humanoid_leggings) as well as over the wings layer. However, since the wings layer cannot accept a trim, it receives a missingno texture in its entirety.

This mod does nothing more than prevent trims from being rendered on the wings layer. This way, armor with trim also works with such a resource pack. (See below for example)



| ![An image of the player wearing a chestplate like the one described while using the mod.](https://cdn.modrinth.com/data/cached_images/6c9bd0a20ca9d6ee3eec8b5e97c34684efe78c4d.png)         | ![An image of the player wearing a chestplate like the one described while NOT using the mod.](https://cdn.modrinth.com/data/cached_images/e2d451509f3c04eb00cbff5f4c4fe4556edf1ba4.png)             |
|--------------|-----------------|
| With the mod | Without the Mod |

*(I had to create a new repository because I needed to set up a brand-new project for the major version update to 26.1)*