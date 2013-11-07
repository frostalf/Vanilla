/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.world.generator.structure;

import org.spout.api.material.BlockMaterial;
import org.spout.api.math.IntVector3;

import org.spout.math.imaginary.Quaternionf;
import org.spout.math.matrix.Matrix3f;
import org.spout.math.vector.Vector3f;

/**
 * Draws a plane with the defined block material layout
 */
public class PieceLayoutBuilder extends PieceBuilder {
	private final IntVector3 position = new IntVector3(0, 0, 0);
	private Quaternionf rotation = Quaternionf.IDENTITY;
	private final IntVector3 rotationPoint = new IntVector3(0, 0, 0);
	private BlockMaterialLayout layout = new BlockMaterialLayout("");

	public PieceLayoutBuilder(StructurePiece parent) {
		super(parent);
	}

	public PieceLayoutBuilder setLayout(BlockMaterialLayout layout) {
		this.layout = layout;
		return this;
	}

	public PieceLayoutBuilder setPosition(int x, int y, int z) {
		setPosition(new IntVector3(x, y, z));
		return this;
	}

	public PieceLayoutBuilder setPosition(IntVector3 position) {
		this.position.set(position);
		return this;
	}

	public PieceLayoutBuilder offsetPosition(int xOff, int yOff, int zOff) {
		offsetPosition(new IntVector3(xOff, yOff, zOff));
		return this;
	}

	public PieceLayoutBuilder offsetPosition(IntVector3 offset) {
		position.add(offset);
		return this;
	}

	public PieceLayoutBuilder setRotation(Quaternionf rotation) {
		this.rotation = rotation;
		return this;
	}

	public PieceLayoutBuilder setRotationPoint(int x, int y, int z) {
		setRotationPoint(new IntVector3(x, y, z));
		return this;
	}

	public PieceLayoutBuilder setRotationPoint(IntVector3 rotationPoint) {
		this.rotationPoint.set(position);
		return this;
	}

	public PieceLayoutBuilder offsetRotationPoint(int xOff, int yOff, int zOff) {
		offsetRotationPoint(new IntVector3(xOff, yOff, zOff));
		return this;
	}

	public PieceLayoutBuilder offsetRotationPoint(IntVector3 offset) {
		rotationPoint.add(offset);
		return this;
	}

	@Override
	public void fill() {
		for (int xx = 0; xx < layout.getRowLenght(); xx++) {
			for (int zz = 0; zz < layout.getColumnLenght(xx); zz++) {
				final BlockMaterial material = layout.getBlockMaterial(xx, zz);
				if (material != null) {
					setBlockMaterial(xx, zz, material);
				}
			}
		}
	}

	private void setBlockMaterial(int xx, int zz, BlockMaterial material) {
		final Vector3f transformed = transform(xx, zz);
		parent.setBlockMaterial(transformed.getFloorX(), transformed.getFloorY(), transformed.getFloorZ(), material);
	}

	private Vector3f transform(int x, int z) {
		final Vector3f rotPoint = new Vector3f(rotationPoint.getX(), rotationPoint.getY(), rotationPoint.getZ());
		return Matrix3f.createRotation(rotation).transform(new Vector3f(x, 0, z).sub(rotPoint)).
				add(rotPoint).add(position.getX(), position.getY(), position.getZ()).round();
	}
}
