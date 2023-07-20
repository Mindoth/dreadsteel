package net.mindoth.dreadsteel.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.mindoth.dreadsteel.entity.EntityScytheProjectileWhite;
import net.mindoth.dreadsteel.registries.DreadsteelItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class RenderScytheProjectileWhite extends EntityRenderer<EntityScytheProjectileWhite> {

    private ItemStack PROJECTILE = new ItemStack(DreadsteelItems.SCYTHE_PROJECTILE_WHITE.get());

    public RenderScytheProjectileWhite(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityScytheProjectileWhite entity) {
        return AtlasTexture.LOCATION_BLOCKS;
    }

    @Override
    public void render(EntityScytheProjectileWhite entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) - 90.0F));
        matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
        matrixStackIn.translate(0, 0.5F, 0);
        matrixStackIn.scale(2F, 2F, 2F);
        //matrixStackIn.mulPose(new Quaternion(Vector3f.YP, 0F, true));
        //matrixStackIn.mulPose(new Quaternion(Vector3f.ZN, (entityIn.tickCount + partialTicks) * 30F, true));
        matrixStackIn.translate(0, -0.15F, 0);
        Minecraft.getInstance().getItemRenderer().renderStatic(PROJECTILE, ItemCameraTransforms.TransformType.GROUND, 240, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.popPose();


    }
}
