package net.mindoth.dreadsteel.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBlack;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileBronze;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RenderScytheProjectileBronze extends EntityRenderer<EntityScytheProjectileBronze> {

    private ItemStack PROJECTILE = new ItemStack(DreadsteelItems.SCYTHE_PROJECTILE_BRONZE.get());

    public RenderScytheProjectileBronze(EntityRendererProvider.Context renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityScytheProjectileBronze entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(EntityScytheProjectileBronze entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        matrixStackIn.translate(0, 0.5F, 0);
        matrixStackIn.scale(2F, 2F, 2F);
        //matrixStackIn.mulPose(new Quaternion(Vector3f.YP, 0F, true));
        //matrixStackIn.mulPose(new Quaternion(Vector3f.ZN, (entityIn.tickCount + partialTicks) * 30F, true));
        matrixStackIn.translate(0, -0.15F, 0);
        Minecraft.getInstance().getItemRenderer().renderStatic(PROJECTILE, ItemDisplayContext.GROUND, 240, 0, matrixStackIn, bufferIn, entityIn.level(), 0);
        matrixStackIn.popPose();
    }
}
