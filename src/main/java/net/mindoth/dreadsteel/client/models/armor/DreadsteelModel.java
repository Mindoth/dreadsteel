package net.mindoth.dreadsteel.client.models.armor;

import net.mindoth.dreadsteel.Dreadsteel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class DreadsteelModel<T extends Entity> extends ArmorModel {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Dreadsteel.MOD_ID, "dreadsteel_armor"), "main");

    public DreadsteelModel(ModelPart part) {
        super(part);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0), 0);
        PartDefinition root = createHumanoidAlias(mesh);

        PartDefinition head = root.getChild("Head");
        PartDefinition body = root.getChild("Body");
        PartDefinition right_arm = root.getChild("RightArm");
        PartDefinition left_arm = root.getChild("LeftArm");
        PartDefinition right_leg = root.getChild("RightLeg");
        PartDefinition left_leg = root.getChild("LeftLeg");
        PartDefinition right_foot = root.getChild("RightBoot");
        PartDefinition left_foot = root.getChild("LeftBoot");



        PartDefinition head1 = head.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(2, 190).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51F))
                .texOffs(207, 71).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(34, 190).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.85F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition HeardHornsR = head.addOrReplaceChild("HeardHornsR", CubeListBuilder.create().texOffs(212, 33).addBox(-3.0F, -5.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1F, -5.5F, -2.1F, -0.4363F, 0.1536F, 0.6665F));

        PartDefinition HeadHornR2_r1 = HeardHornsR.addOrReplaceChild("HeadHornR2_r1", CubeListBuilder.create().texOffs(214, 41).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -3.7F, -0.5F, -0.436F, 0.0184F, -0.0041F));

        PartDefinition HeadHornR1_r1 = HeardHornsR.addOrReplaceChild("HeadHornR1_r1", CubeListBuilder.create().texOffs(216, 48).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.5F, 0.0F, -0.829F, 0.0F, 0.0F));

        PartDefinition HeadHornsL = head.addOrReplaceChild("HeadHornsL", CubeListBuilder.create().texOffs(212, 33).addBox(-3.0F, -5.0F, -2.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9F, -7.5F, -1.7F, -0.4363F, -0.1536F, -0.6665F));

        PartDefinition HeadHornL2_r1 = HeadHornsL.addOrReplaceChild("HeadHornL2_r1", CubeListBuilder.create().texOffs(214, 41).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -3.7F, -0.5F, -0.436F, 0.0184F, -0.0041F));

        PartDefinition HeadHornL1_r1 = HeadHornsL.addOrReplaceChild("HeadHornL1_r1", CubeListBuilder.create().texOffs(216, 48).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -6.5F, 0.0F, -0.829F, 0.0F, 0.0F));

        PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(18, 206).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.49F))
                .texOffs(155, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.3F))
                .texOffs(230, 134).addBox(-4.0F, 1.0F, -3.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(233, 107).addBox(-4.0F, 1.0F, 2.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(231, 125).addBox(-3.0F, 7.0F, -2.8F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(236, 112).addBox(-3.0F, 7.0F, 2.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(207, 65).addBox(-4.5F, 10.6F, -2.9F, 9.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(247, 84).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2F, 18.5F, -3.9F, -0.1309F, 0.0F, -0.3491F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(247, 84).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, 18.8F, -3.9F, -0.1309F, 0.0F, 0.3491F));

        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(245, 70).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 12.3F, -3.5F, -0.0928F, -0.0924F, -0.7811F));

        PartDefinition Belt_r1 = body.addOrReplaceChild("Belt_r1", CubeListBuilder.create().texOffs(155, 46).addBox(-9.0F, -1.0F, -2.8F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 11.4F, 1.0F, 0.3054F, 0.0F, 0.0F));

        PartDefinition RightArm = right_arm.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(42, 206).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F))
                .texOffs(187, 53).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
                .texOffs(226, 51).addBox(0.0F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(210, 51).addBox(-1.0F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r4 = RightArm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(209, 152).addBox(-16.0F, -0.6F, -2.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.8F, -13.9F, 0.0F, 1.5708F, 0.0F));

        PartDefinition RShoulderHorns3 = RightArm.addOrReplaceChild("RShoulderHorns3", CubeListBuilder.create().texOffs(213, 27).addBox(-3.5969F, -1.8727F, -1.2783F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -0.7F, 1.4F, 0.0F, 0.4363F, 0.6545F));

        PartDefinition RShoulderHorns4 = RShoulderHorns3.addOrReplaceChild("RShoulderHorns4", CubeListBuilder.create().texOffs(214, 25).addBox(-3.5437F, -0.7226F, -0.2783F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.2F, -0.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition RShoulderHorns1 = RightArm.addOrReplaceChild("RShoulderHorns1", CubeListBuilder.create().texOffs(213, 27).addBox(-3.3812F, -2.0554F, -0.8222F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3F, -0.7F, -1.4F, 0.0F, -0.4363F, 0.6545F));

        PartDefinition RShoulderHorns2 = RShoulderHorns1.addOrReplaceChild("RShoulderHorns2", CubeListBuilder.create().texOffs(214, 25).addBox(-3.3826F, -0.9548F, 0.1778F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.2F, -0.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition LeftArm = left_arm.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(2, 238).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.6F))
                .texOffs(187, 53).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F))
                .texOffs(210, 51).addBox(0.1F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(226, 51).addBox(-0.9F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = LeftArm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(209, 145).addBox(1.0F, -12.6F, 5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 16.8F, -2.9F, 0.0F, -1.5708F, 0.0F));

        PartDefinition LShoulderHorns3 = LeftArm.addOrReplaceChild("LShoulderHorns3", CubeListBuilder.create().texOffs(213, 27).addBox(-3.5969F, -1.8728F, -1.2783F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -0.7F, -1.4F, -3.1416F, -0.4363F, 2.4871F));

        PartDefinition LShoulderHorns4 = LShoulderHorns3.addOrReplaceChild("LShoulderHorns4", CubeListBuilder.create().texOffs(214, 25).addBox(-3.5437F, -0.7226F, -0.2783F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.2F, -0.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition LShoulderHorns1 = LeftArm.addOrReplaceChild("LShoulderHorns1", CubeListBuilder.create().texOffs(213, 27).addBox(-3.5969F, -1.8728F, -0.7217F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -0.7F, 1.4F, 3.1416F, 0.4363F, 2.4871F));

        PartDefinition LShoulderHorns2 = LShoulderHorns1.addOrReplaceChild("LShoulderHorns2", CubeListBuilder.create().texOffs(214, 25).addBox(-3.5437F, -0.7226F, 0.2783F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.2F, -0.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition LeftLeg = left_leg.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(18, 238).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F))
                .texOffs(227, 147).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightLeg = right_leg.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(235, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F))
                .texOffs(206, 159).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition CoatTailRight = LeftLeg.addOrReplaceChild("CoatTailRight", CubeListBuilder.create(), PartPose.offsetAndRotation(1.9F, 13.2F, -1.4F, 0.0F, -0.1309F, 0.0F));

        PartDefinition RightCoatTailEx_r1 = CoatTailRight.addOrReplaceChild("RightCoatTailEx_r1", CubeListBuilder.create().texOffs(155, 53).addBox(-6.6172F, -12.4538F, 3.2715F, 1.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2607F, 0.0927F, 0.1602F));

        PartDefinition RightCoatTail_r1 = CoatTailRight.addOrReplaceChild("RightCoatTail_r1", CubeListBuilder.create().texOffs(176, 54).addBox(-4.9044F, -12.4737F, 1.9621F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.3F, 4.8F, 0.2597F, -0.0338F, 0.1265F));

        PartDefinition CoatTailLeft = RightLeg.addOrReplaceChild("CoatTailLeft", CubeListBuilder.create(), PartPose.offset(-2.4F, 13.3F, -1.7F));

        PartDefinition LeftCoatTailEx_r1 = CoatTailLeft.addOrReplaceChild("LeftCoatTailEx_r1", CubeListBuilder.create().texOffs(155, 53).addBox(4.9268F, -12.4442F, 4.1603F, 1.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8F, 0.1F, -0.3F, 0.2597F, 0.0338F, -0.1265F));

        PartDefinition LeftCoatTail_r1 = CoatTailLeft.addOrReplaceChild("LeftCoatTail_r1", CubeListBuilder.create().texOffs(165, 54).addBox(0.9065F, -12.4442F, 7.1494F, 4.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.4F, 0.263F, 0.1603F, -0.0925F));

        PartDefinition RightBoot = right_foot.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(209, 152).addBox(-4.0F, -6.2F, -2.9F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 244).addBox(-4.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition LeftBoot = left_foot.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(209, 145).addBox(0.0F, -6.2F, -2.9F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 244).addBox(0.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 256, 256);
    }


    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
