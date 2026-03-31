package net.quarxy.mixin.client;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EquipmentLayerRenderer.class)
@Debug(export = true)
public class EquipmentLayerRendererMixin<S> {

	@Definition(id = "layerType", local = @Local(type = EquipmentClientInfo.LayerType.class, name = "layerType", argsOnly = true))
	@Definition(id = "HUMANOID_BABY", field = "Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;HUMANOID_BABY:Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;")
	@Expression("layerType == HUMANOID_BABY")
	@ModifyExpressionValue(
			method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/Identifier;II)V",
			at = @At("MIXINEXTRAS:EXPRESSION")
	)
	private boolean excludeWingsFromTrim(boolean original, @Local(argsOnly = true, name = "layerType") EquipmentClientInfo.LayerType layerType) {
		return original || layerType == EquipmentClientInfo.LayerType.WINGS;
	}

}