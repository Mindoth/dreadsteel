package net.mindoth.dreadsteel.client.models.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.inventory.EquipmentSlotType;

public class DreadsteelModel extends BipedModel<LivingEntity> {
    private final EquipmentSlotType slot;

    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer RightLeg;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightFoot;
    private final ModelRenderer LeftFoot;

    public DreadsteelModel(EquipmentSlotType slot) {
        super(0.0F, 1.0f, 128, 128);
        this.slot = slot;
        texWidth = 256;
        texHeight = 256;

        Head = new ModelRenderer(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        Head.texOffs(2, 190).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.51F, false);
        Head.texOffs(207, 71).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        Head.texOffs(34, 190).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.85F, false);

        ModelRenderer heardHornsR = new ModelRenderer(this);
        heardHornsR.setPos(4.1F, -5.5F, -2.1F);
        Head.addChild(heardHornsR);
        setRotation(heardHornsR, -0.4363F, 0.1536F, 0.6665F);
        heardHornsR.texOffs(212, 33).addBox(-3.0F, -5.0F, -2.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        ModelRenderer headHornR2_r1 = new ModelRenderer(this);
        headHornR2_r1.setPos(-0.5F, -3.7F, -0.5F);
        heardHornsR.addChild(headHornR2_r1);
        setRotation(headHornR2_r1, -0.436F, 0.0184F, -0.0041F);
        headHornR2_r1.texOffs(214, 41).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        ModelRenderer headHornR1_r1 = new ModelRenderer(this);
        headHornR1_r1.setPos(-1.0F, -6.5F, 0.0F);
        heardHornsR.addChild(headHornR1_r1);
        setRotation(headHornR1_r1, -0.829F, 0.0F, 0.0F);
        headHornR1_r1.texOffs(216, 48).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer headHornsL = new ModelRenderer(this);
        headHornsL.setPos(-1.9F, -7.5F, -1.7F);
        Head.addChild(headHornsL);
        setRotation(headHornsL, -0.4363F, -0.1536F, -0.6665F);
        headHornsL.texOffs(212, 33).addBox(-3.0F, -5.0F, -2.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        ModelRenderer headHornL2_r1 = new ModelRenderer(this);
        headHornL2_r1.setPos(-0.5F, -3.7F, -0.5F);
        headHornsL.addChild(headHornL2_r1);
        setRotation(headHornL2_r1, -0.436F, 0.0184F, -0.0041F);
        headHornL2_r1.texOffs(214, 41).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        ModelRenderer headHornL1_r1 = new ModelRenderer(this);
        headHornL1_r1.setPos(-1.0F, -6.5F, 0.0F);
        headHornsL.addChild(headHornL1_r1);
        setRotation(headHornL1_r1, -0.829F, 0.0F, 0.0F);
        headHornL1_r1.texOffs(216, 48).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        Body = new ModelRenderer(this);
        Body.setPos(0.0F, 0.0F, 0.0F);
        Body.texOffs(18, 206).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.49F, false);
        Body.texOffs(155, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.3F, false);
        Body.texOffs(230, 134).addBox(-4.0F, 1.0F, -3.0F, 8.0F, 6.0F, 1.0F, 0.0F, false);
        Body.texOffs(233, 107).addBox(-4.0F, 1.0F, 2.0F, 8.0F, 6.0F, 1.0F, 0.0F, false);
        Body.texOffs(231, 125).addBox(-3.0F, 7.0F, -2.8F, 6.0F, 4.0F, 1.0F, 0.0F, false);
        Body.texOffs(236, 112).addBox(-3.0F, 7.0F, 2.0F, 6.0F, 3.0F, 1.0F, 0.0F, false);
        Body.texOffs(207, 65).addBox(-4.5F, 10.6F, -2.9F, 9.0F, 2.0F, 3.0F, 0.0F, false);

        ModelRenderer cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(3.2F, 18.5F, -3.9F);
        Body.addChild(cube_r1);
        setRotation(cube_r1, -0.1309F, 0.0F, -0.3491F);
        cube_r1.texOffs(247, 84).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(-2.3F, 18.8F, -3.9F);
        Body.addChild(cube_r2);
        setRotation(cube_r2, -0.1309F, 0.0F, 0.3491F);
        cube_r2.texOffs(247, 84).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

        ModelRenderer cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(0.7F, 12.3F, -3.5F);
        Body.addChild(cube_r3);
        setRotation(cube_r3, -0.0928F, -0.0924F, -0.7811F);
        cube_r3.texOffs(245, 70).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        ModelRenderer belt_r1 = new ModelRenderer(this);
        belt_r1.setPos(4.0F, 11.4F, 1.0F);
        Body.addChild(belt_r1);
        setRotation(belt_r1, 0.3054F, 0.0F, 0.0F);
        belt_r1.texOffs(155, 46).addBox(-9.0F, -1.0F, -2.8F, 10.0F, 2.0F, 5.0F, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        RightArm.texOffs(42, 206).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.6F, false);
        RightArm.texOffs(187, 53).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.4F, false);
        RightArm.texOffs(226, 51).addBox(0.0F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, 0.0F, false);
        RightArm.texOffs(210, 51).addBox(-1.0F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, 0.0F, false);

        ModelRenderer cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(-2.0F, 4.8F, -13.9F);
        RightArm.addChild(cube_r4);
        setRotation(cube_r4, 0.0F, 1.5708F, 0.0F);
        cube_r4.texOffs(209, 152).addBox(-16.0F, -0.6F, -2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        ModelRenderer RShoulderHorns3 = new ModelRenderer(this);
        RShoulderHorns3.setPos(-3.0F, -0.7F, 1.4F);
        RightArm.addChild(RShoulderHorns3);
        setRotation(RShoulderHorns3, 0.0F, 0.4363F, 0.6545F);
        RShoulderHorns3.texOffs(213, 27).addBox(-3.5969F, -1.8727F, -1.2783F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer RShoulderHorns4 = new ModelRenderer(this);
        RShoulderHorns4.setPos(-2.0F, -0.2F, -0.5F);
        RShoulderHorns3.addChild(RShoulderHorns4);
        setRotation(RShoulderHorns4, 0.0F, 0.0F, 0.2618F);
        RShoulderHorns4.texOffs(214, 25).addBox(-3.5437F, -0.7226F, -0.2783F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer RShoulderHorns1 = new ModelRenderer(this);
        RShoulderHorns1.setPos(-3.3F, -0.7F, -1.4F);
        RightArm.addChild(RShoulderHorns1);
        setRotation(RShoulderHorns1, 0.0F, -0.4363F, 0.6545F);
        RShoulderHorns1.texOffs(213, 27).addBox(-3.3812F, -2.0554F, -0.8222F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer RShoulderHorns2 = new ModelRenderer(this);
        RShoulderHorns2.setPos(-2.0F, -0.2F, -0.5F);
        RShoulderHorns1.addChild(RShoulderHorns2);
        setRotation(RShoulderHorns2, 0.0F, 0.0F, 0.2618F);
        RShoulderHorns2.texOffs(214, 25).addBox(-3.3826F, -0.9548F, 0.1778F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        LeftArm.texOffs(2, 238).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.6F, false);
        LeftArm.texOffs(187, 53).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.4F, false);
        LeftArm.texOffs(210, 51).addBox(0.1F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, 0.0F, false);
        LeftArm.texOffs(226, 51).addBox(-0.9F, -3.3F, -3.5F, 1.0F, 5.0F, 7.0F, 0.0F, false);

        ModelRenderer cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(9.0F, 16.8F, -2.9F);
        LeftArm.addChild(cube_r5);
        setRotation(cube_r5, 0.0F, -1.5708F, 0.0F);
        cube_r5.texOffs(209, 145).addBox(1.0F, -12.6F, 5.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        ModelRenderer LShoulderHorns3 = new ModelRenderer(this);
        LShoulderHorns3.setPos(3.0F, -0.7F, -1.4F);
        LeftArm.addChild(LShoulderHorns3);
        setRotation(LShoulderHorns3, -3.1416F, -0.4363F, 2.4871F);
        LShoulderHorns3.texOffs(213, 27).addBox(-3.5969F, -1.8728F, -1.2783F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer LShoulderHorns4 = new ModelRenderer(this);
        LShoulderHorns4.setPos(-2.0F, -0.2F, -0.5F);
        LShoulderHorns3.addChild(LShoulderHorns4);
        setRotation(LShoulderHorns4, 0.0F, 0.0F, 0.2618F);
        LShoulderHorns4.texOffs(214, 25).addBox(-3.5437F, -0.7226F, -0.2783F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        ModelRenderer LShoulderHorns1 = new ModelRenderer(this);
        LShoulderHorns1.setPos(3.0F, -0.7F, 1.4F);
        LeftArm.addChild(LShoulderHorns1);
        setRotation(LShoulderHorns1, 3.1416F, 0.4363F, 2.4871F);
        LShoulderHorns1.texOffs(213, 27).addBox(-3.5969F, -1.8728F, -0.7217F, 4.0F, 2.0F, 2.0F, 0.0F, false);

        ModelRenderer LShoulderHorns2 = new ModelRenderer(this);
        LShoulderHorns2.setPos(-2.0F, -0.2F, -0.5F);
        LShoulderHorns1.addChild(LShoulderHorns2);
        setRotation(LShoulderHorns2, 0.0F, 0.0F, 0.2618F);
        LShoulderHorns2.texOffs(214, 25).addBox(-3.5437F, -0.7226F, 0.2783F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(-1.9F, 12.0F, 0.0F);
        RightLeg.texOffs(18, 238).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);
        RightLeg.texOffs(227, 147).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.5F, false);

        ModelRenderer coatTailRight = new ModelRenderer(this);
        coatTailRight.setPos(1.9F, 13.2F, -1.4F);
        RightLeg.addChild(coatTailRight);
        setRotation(coatTailRight, 0.0F, -0.1309F, 0.0F);


        ModelRenderer rightCoatTailEx_r1 = new ModelRenderer(this);
        rightCoatTailEx_r1.setPos(0.0F, 0.0F, 0.0F);
        coatTailRight.addChild(rightCoatTailEx_r1);
        setRotation(rightCoatTailEx_r1, 0.2607F, 0.0927F, 0.1602F);
        rightCoatTailEx_r1.texOffs(155, 53).addBox(-6.6172F, -12.4538F, 3.2715F, 1.0F, 10.0F, 4.0F, 0.0F, false);

        ModelRenderer rightCoatTail_r1 = new ModelRenderer(this);
        rightCoatTail_r1.setPos(0.0F, -1.3F, 4.8F);
        coatTailRight.addChild(rightCoatTail_r1);
        setRotation(rightCoatTail_r1, 0.2597F, -0.0338F, 0.1265F);
        rightCoatTail_r1.texOffs(176, 54).addBox(-4.9044F, -12.4737F, 1.9621F, 4.0F, 10.0F, 1.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(1.9F, 12.0F, 0.0F);
        LeftLeg.texOffs(235, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.26F, false);
        LeftLeg.texOffs(206, 159).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.5F, false);

        ModelRenderer coatTailLeft = new ModelRenderer(this);
        coatTailLeft.setPos(-2.4F, 13.3F, -1.7F);
        LeftLeg.addChild(coatTailLeft);


        ModelRenderer leftCoatTailEx_r1 = new ModelRenderer(this);
        leftCoatTailEx_r1.setPos(0.8F, 0.1F, -0.3F);
        coatTailLeft.addChild(leftCoatTailEx_r1);
        setRotation(leftCoatTailEx_r1, 0.2597F, 0.0338F, -0.1265F);
        leftCoatTailEx_r1.texOffs(155, 53).addBox(4.9268F, -12.4442F, 4.1603F, 1.0F, 10.0F, 4.0F, 0.0F, false);

        ModelRenderer leftCoatTail_r1 = new ModelRenderer(this);
        leftCoatTail_r1.setPos(0.0F, 0.0F, 0.4F);
        coatTailLeft.addChild(leftCoatTail_r1);
        setRotation(leftCoatTail_r1, 0.263F, 0.1603F, -0.0925F);
        leftCoatTail_r1.texOffs(165, 54).addBox(0.9065F, -12.4442F, 7.1494F, 4.0F, 10.0F, 1.0F, 0.0F, false);

        RightFoot = new ModelRenderer(this);
        RightFoot.setPos(0.0F, 24.0F, 0.0F);
        RightFoot.texOffs(209, 152).addBox(-2.1F, 6.2F, -2.9F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        RightFoot.texOffs(58, 244).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.4F, false);

        LeftFoot = new ModelRenderer(this);
        LeftFoot.setPos(0.0F, 24.0F, 0.0F);
        LeftFoot.texOffs(209, 145).addBox(-1.9F, 6.2F, -2.9F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        LeftFoot.texOffs(80, 244).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.4F, false);
    }

    @Override
    public void setupAnim(LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity instanceof ArmorStandEntity) {
            ArmorStandEntity armorStand = (ArmorStandEntity) livingEntity;
            this.Head.xRot = ((float) Math.PI / 180F) * armorStand.getHeadPose().getX();
            this.Head.yRot = ((float) Math.PI / 180F) * armorStand.getHeadPose().getY();
            this.Head.zRot = ((float) Math.PI / 180F) * armorStand.getHeadPose().getZ();
            this.Head.setPos(0.0F, 1.0F, 0.0F);
            this.Body.xRot = ((float) Math.PI / 180F) * armorStand.getBodyPose().getX();
            this.Body.yRot = ((float) Math.PI / 180F) * armorStand.getBodyPose().getY();
            this.Body.zRot = ((float) Math.PI / 180F) * armorStand.getBodyPose().getZ();
            this.LeftArm.xRot = ((float) Math.PI / 180F) * armorStand.getLeftArmPose().getX();
            this.LeftArm.yRot = ((float) Math.PI / 180F) * armorStand.getLeftArmPose().getY();
            this.LeftArm.zRot = ((float) Math.PI / 180F) * armorStand.getLeftArmPose().getZ();
            this.RightArm.xRot = ((float) Math.PI / 180F) * armorStand.getRightArmPose().getX();
            this.RightArm.yRot = ((float) Math.PI / 180F) * armorStand.getRightArmPose().getY();
            this.RightArm.zRot = ((float) Math.PI / 180F) * armorStand.getRightArmPose().getZ();
            this.LeftLeg.xRot = ((float) Math.PI / 180F) * armorStand.getLeftLegPose().getX();
            this.LeftLeg.yRot = ((float) Math.PI / 180F) * armorStand.getLeftLegPose().getY();
            this.LeftLeg.zRot = ((float) Math.PI / 180F) * armorStand.getLeftLegPose().getZ();
            this.LeftLeg.setPos(1.9F, 11.0F, 0.0F);
            this.RightLeg.xRot = ((float) Math.PI / 180F) * armorStand.getRightLegPose().getX();
            this.RightLeg.yRot = ((float) Math.PI / 180F) * armorStand.getRightLegPose().getY();
            this.RightLeg.zRot = ((float) Math.PI / 180F) * armorStand.getRightLegPose().getZ();
            this.RightLeg.setPos(-1.9F, 11.0F, 0.0F);
            this.hat.copyFrom(this.Head);
        } else {
            super.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        matrixStack.pushPose();

        this.setHeadRotation();
        this.setChestRotation();
        this.setLegsRotation();
        this.setBootRotation();

        Head.visible = slot == EquipmentSlotType.HEAD;
        Body.visible = slot == EquipmentSlotType.CHEST;
        RightArm.visible = slot == EquipmentSlotType.CHEST;
        LeftArm.visible = slot == EquipmentSlotType.CHEST;
        RightLeg.visible = slot == EquipmentSlotType.LEGS;
        LeftLeg.visible = slot == EquipmentSlotType.LEGS;
        RightFoot.visible = slot == EquipmentSlotType.FEET;
        LeftFoot.visible = slot == EquipmentSlotType.FEET;
        if (this.young) {
            float f = 2.0F;
            matrixStack.scale(1.5F / f, 1.5F / f, 1.5F / f);
            matrixStack.translate(0.0F, 16.0F * 1, 0.0F);
            Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            matrixStack.popPose();
            matrixStack.pushPose();
            matrixStack.scale(1.0F / f, 1.0F / f, 1.0F / f);
            matrixStack.translate(0.0F, 24.0F * 1, 0.0F);
            Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
        else {
            Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }
        RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftFoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        matrixStack.popPose();
    }

    public void setHeadRotation() {
        Head.x = head.x;
        Head.y = head.y;
        Head.z = head.z;
        setRotation(Head, head.xRot, head.yRot, head.zRot);
    }

    public void setChestRotation() {
        /* if (e instanceof EntityPlayer){ ((EntityPlayer)e).get } */
        Body.x = body.x;
        Body.y = body.y;
        Body.z = body.z;
        RightArm.x = rightArm.x;
        RightArm.y = rightArm.y;
        RightArm.z = rightArm.z;
        LeftArm.x = leftArm.x;
        LeftArm.y = leftArm.y;
        LeftArm.z = leftArm.z;
        setRotation(Body, body.xRot, body.yRot, body.zRot);
        setRotation(RightArm, rightArm.xRot, rightArm.yRot, rightArm.zRot);
        setRotation(LeftArm, leftArm.xRot, leftArm.yRot, leftArm.zRot);
    }

    public void setLegsRotation() {
        RightLeg.x = rightLeg.x;
        RightLeg.y = rightLeg.y;
        RightLeg.z = rightLeg.z;
        LeftLeg.x = leftLeg.x;
        LeftLeg.y = leftLeg.y;
        LeftLeg.z = leftLeg.z;
        setRotation(RightLeg, rightLeg.xRot, rightLeg.yRot, rightLeg.zRot);
        setRotation(LeftLeg, leftLeg.xRot, leftLeg.yRot, leftLeg.zRot);
    }

    public void setBootRotation() {
        RightFoot.x = rightLeg.x;
        RightFoot.y = rightLeg.y;
        RightFoot.z = rightLeg.z;
        LeftFoot.x = leftLeg.x;
        LeftFoot.y = leftLeg.y;
        LeftFoot.z = leftLeg.z;
        setRotation(RightFoot, rightLeg.xRot, rightLeg.yRot, rightLeg.zRot);
        setRotation(LeftFoot, leftLeg.xRot, leftLeg.yRot, leftLeg.zRot);
    }

    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
