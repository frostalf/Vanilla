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
package org.spout.vanilla.component.entity.living.passive;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import org.spout.vanilla.VanillaPlugin;
import org.spout.vanilla.component.entity.living.Animal;
import org.spout.vanilla.component.entity.misc.Health;
import org.spout.vanilla.data.VanillaData;
import org.spout.vanilla.protocol.entity.creature.HorseEntityProtocol;

public class Horse extends Animal {

	@Override
	public void onAttached() {
		getOwner().getNetwork().setEntityProtocol(VanillaPlugin.VANILLA_PROTOCOL_ID, new HorseEntityProtocol());
		if (getAttachedCount() == 1) {
			getOwner().add(Health.class).setSpawnHealth(22);
		}
	}

	public boolean isTamed() {
		return getOwner().getData().get(VanillaData.TAMED);
	}

	public void setTamed(boolean tamed) {
		getOwner().getData().put(VanillaData.TAMED, tamed);
	}

	public boolean canTame() {
		return getHorseTypeId() <= 2;
	}

	public String getOwnerName() {
		return getOwner().getData().get(VanillaData.OWNER);
	}

	public void setOwnerName(String owner) {
		if (isTamed()) {
			getOwner().getData().put(VanillaData.OWNER, owner);
		}
	}

	public void setVariantAndMarking(Variant variant, Marking marking) {

	}

	public void setVariant(Variant variant) {

	}

	public Variant getVariant() {
		return null;
	}

	public void setMarking(Marking marking) {

	}

	public Marking getMarking() {
		return null;
	}

	/**
	 * The value associated with this horse type.
	 *
	 * @return byte
	 */
	public byte getHorseTypeId() {
		return 0;
	}

	public enum Variant {
		WHITE(0x0),
		CREAMY(0x1),
		CHESTNUT(0x2),
		BROWN(0x3),
		BLACK(0x4),
		GRAY(0x5),
		DARK_BROWN(0x6);
		private final int id;

		Variant(int id) {
			this.id = id;
		}

		public int getVariantId() {
			return this.id;
		}

		public static Variant fromId(int id) {
			if (id > Variant.values().length - 1 || id < 0) {
				return null;
			}
			return Variant.values()[id];
		}
	}

	public enum Marking {
		NONE(0x0),
		WHITE(256),
		WHITEFIELD(512),
		WHITEDOTS(768),
		BLACKDOTS(1024);
		private final int markingId;
		private static final TIntObjectMap<Marking> ids = new TIntObjectHashMap<Marking>();

		Marking(int markingId) {
			this.markingId = markingId;
		}

		public int getMarkingId() {
			return this.markingId;
		}

		public static Marking fromId(int id) {
			return ids.get(id);
		}

		static {
			for (Marking marking : Marking.values()) {
				ids.put(marking.getMarkingId(), marking);
			}
		}
	}
}