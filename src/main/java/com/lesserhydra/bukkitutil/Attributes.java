// The MIT License (MIT)
//
// Copyright (c) 2015 Kristian Stangeland
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation 
// files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, 
// modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the 
// Software is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or substantial portions of the 
// Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
// COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
// ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

////
// Please note that this is NOT an original copy, but has
// been unceremoniously butchered for use in HydraCore.
// - Justin

package com.lesserhydra.bukkitutil;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.lesserhydra.bukkitutil.nbt.NbtCompound;
import com.lesserhydra.bukkitutil.nbt.NbtFactory;
import com.lesserhydra.bukkitutil.nbt.NbtList;
import com.lesserhydra.bukkitutil.nbt.NbtType;
import com.lesserhydra.bukkitutil.volatilecode.MirrorItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;


@SuppressWarnings({"WeakerAccess", "unused"})
public class Attributes {
  
  public enum Slot {
    ALL(""),
    MAIN_HAND("mainhand"),
    OFF_HAND("offhand"),
    HEAD("head"),
    CHEST("chest"),
    LEGS("legs"),
    FEET("feet");
    private final String id;
    
    Slot(String id) {
      this.id = id;
    }
    
    public String getId() {
      return id;
    }
    
    public static Slot fromId(String id) {
      for (Slot slot : values()) {
        if (slot.id.equals(id)) return slot;
      }
      throw new IllegalArgumentException("Unknown slot ID \"" + id + "\" detected.");
    }
  }
  
  public enum Operation {
    ADD_NUMBER(0),
    MULTIPLY_PERCENTAGE(1),
    ADD_PERCENTAGE(2);
    private int id;
    
    Operation(int id) {
      this.id = id;
    }
    
    public int getId() {
      return id;
    }
    
    public static Operation fromId(int id) {
      // Linear scan is very fast for small N
      for (Operation op : values()) {
        if (op.getId() == id) {
          return op;
        }
      }
      throw new IllegalArgumentException("Corrupt operation ID " + id + " detected.");
    }
  }
  
  public static class AttributeType {
    private static ConcurrentMap<String, AttributeType> LOOKUP = Maps.newConcurrentMap();
    public static final AttributeType GENERIC_MAX_HEALTH = new AttributeType("generic.maxHealth").register();
    public static final AttributeType GENERIC_FOLLOW_RANGE = new AttributeType("generic.followRange").register();
    public static final AttributeType GENERIC_ATTACK_DAMAGE = new AttributeType("generic.attackDamage").register();
    public static final AttributeType GENERIC_MOVEMENT_SPEED = new AttributeType("generic.movementSpeed").register();
    public static final AttributeType GENERIC_KNOCKBACK_RESISTANCE = new AttributeType("generic.knockbackResistance").register();
    public static final AttributeType GENERIC_ARMOR = new AttributeType("generic.armor").register();
    public static final AttributeType GENERIC_ARMOR_TOUGHNESS = new AttributeType("generic.armorToughness").register();
    public static final AttributeType GENERIC_ATTACK_SPEED = new AttributeType("generic.attackSpeed").register();
    public static final AttributeType GENERIC_LUCK = new AttributeType("generic.luck").register();
    
    private final String minecraftId;
    
    /**
     * Construct a new attribute type.
     * <p>
     * Remember to {@link #register()} the type.
     *
     * @param minecraftId - the ID of the type.
     */
    public AttributeType(String minecraftId) {
      this.minecraftId = minecraftId;
    }
    
    /**
     * Retrieve the associated minecraft ID.
     *
     * @return The associated ID.
     */
    public String getMinecraftId() {
      return minecraftId;
    }
    
    /**
     * Register the type in the central registry.
     *
     * @return The registered type.
     */
    // Constructors should have no side-effects!
    public AttributeType register() {
      AttributeType old = LOOKUP.putIfAbsent(minecraftId, this);
      return old != null ? old : this;
    }
    
    /**
     * Retrieve the attribute type associated with a given ID.
     *
     * @param minecraftId The ID to search for.
     * @return The attribute type, or NULL if not found.
     */
    public static AttributeType fromId(String minecraftId) {
      return LOOKUP.get(minecraftId);
    }
    
    /**
     * Retrieve every registered attribute type.
     *
     * @return Every type.
     */
    public static Iterable<AttributeType> values() {
      return LOOKUP.values();
    }
  }
  
  public static class Attribute {
    private NbtCompound data;
    
    private Attribute(Builder builder) {
      data = NbtFactory.makeCompound();
      setAmount(builder.amount);
      setOperation(builder.operation);
      setSlot(builder.slot);
      setAttributeType(builder.type);
      setName(builder.name);
      setUUID(builder.uuid);
    }
    
    private Attribute(NbtCompound data) {
      this.data = data;
    }
    
    public double getAmount() {
      return data.getDouble("Amount");
    }
    
    public void setAmount(double amount) {
      data.set("Amount", amount);
    }
    
    public Operation getOperation() {
      return Operation.fromId(data.getInteger("Operation"));
    }
    
    public void setOperation(@NotNull Operation operation) {
      Preconditions.checkNotNull(operation, "operation cannot be NULL.");
      data.set("Operation", operation.getId());
    }
    
    public Slot getSlot() {
      return Slot.fromId(data.getString("Slot"));
    }
    
    public void setSlot(@NotNull Slot slot) {
      Preconditions.checkNotNull(slot, "slot cannot be NULL.");
      if (slot == Slot.ALL) data.remove("Slot");
      else data.set("Slot", slot.getId());
    }
    
    public AttributeType getAttributeType() {
      return AttributeType.fromId(data.getString("AttributeName"));
    }
    
    public void setAttributeType(@NotNull AttributeType type) {
      Preconditions.checkNotNull(type, "type cannot be NULL.");
      data.set("AttributeName", type.getMinecraftId());
    }
    
    public String getName() {
      return data.getString("Name");
    }
    
    public void setName(@NotNull String name) {
      Preconditions.checkNotNull(name, "name cannot be NULL.");
      data.set("Name", name);
    }
    
    public UUID getUUID() {
      return data.getUUID("UUID");
    }
    
    public void setUUID(@NotNull UUID id) {
      Preconditions.checkNotNull(id, "id cannot be NULL.");
      data.setUUID("UUID", id);
    }
    
    /**
     * Construct a new attribute builder with a random UUID and default operation of adding numbers.
     *
     * @return The attribute builder.
     */
    public static Builder newBuilder() {
      return new Builder().uuid(UUID.randomUUID()).operation(Operation.ADD_NUMBER);
    }
    
    // Makes it easier to construct an attribute
    public static class Builder {
      private double amount;
      private Operation operation = Operation.ADD_NUMBER;
      private Slot slot = Slot.ALL;
      private AttributeType type;
      private String name;
      private UUID uuid;
      
      private Builder() {
        // Don't make this accessible
      }
      
      public Builder amount(double amount) {
        this.amount = amount;
        return this;
      }
      
      public Builder operation(Operation operation) {
        this.operation = operation;
        return this;
      }
      
      public Builder slot(Slot slot) {
        this.slot = slot;
        return this;
      }
      
      public Builder type(AttributeType type) {
        this.type = type;
        return this;
      }
      
      public Builder name(String name) {
        this.name = name;
        return this;
      }
      
      public Builder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
      }
      
      public Attribute build() {
        return new Attribute(this);
      }
    }
  }
  
  // This may be modified
  public MirrorItemStack stack;
  private NbtList attributes;
  
  public Attributes(ItemStack stack) {
    // Create a CraftItemStack (under the hood)
    this.stack = InventoryUtil.getMirrorItemStack(stack);
    loadAttributes(false);
  }
  
  /**
   * Load the NBT list from the TAG compound.
   *
   * @param createIfMissing - create the list if its missing.
   */
  private void loadAttributes(boolean createIfMissing) {
    if (this.attributes == null) {
      NbtCompound nbt = InventoryUtil.getItemTag(this.stack, createIfMissing);
      if (nbt != null) {
        this.attributes = nbt.getList("AttributeModifiers", NbtType.COMPOUND, createIfMissing);
      }
    }
  }
  
  /**
   * Remove the NBT list from the TAG compound.
   */
  private void removeAttributes() {
    NbtCompound nbt = InventoryUtil.getItemTag(this.stack, false);
    if (nbt != null) nbt.remove("AttributeModifiers");
    this.attributes = null;
  }
  
  /**
   * Retrieve the modified item stack.
   *
   * @return The modified item stack.
   */
  public MirrorItemStack getStack() {
    return stack;
  }
  
  /**
   * Retrieve the number of attributes.
   *
   * @return Number of attributes.
   */
  public int size() {
    return attributes != null ? attributes.size() : 0;
  }
  
  /**
   * Add a new attribute to the list.
   *
   * @param attribute - the new attribute.
   */
  public void add(Attribute attribute) {
    Preconditions.checkNotNull(attribute.getName(), "must specify an attribute name.");
    loadAttributes(true);
    attributes.add(attribute.data);
  }
  
  /**
   * Remove the first instance of the given attribute.
   * <p>
   * The attribute will be removed using its UUID.
   *
   * @param attribute - the attribute to remove.
   * @return TRUE if the attribute was removed, FALSE otherwise.
   */
  public boolean remove(Attribute attribute) {
    if (attributes == null)
      return false;
    UUID uuid = attribute.getUUID();
    
    for (Iterator<Attribute> it = values().iterator(); it.hasNext(); ) {
      if (Objects.equal(it.next().getUUID(), uuid)) {
        it.remove();
        
        // Last removed attribute?
        if (size() == 0) {
          removeAttributes();
        }
        return true;
      }
    }
    return false;
  }
  
  /**
   * Remove every attribute.
   */
  public void clear() {
    removeAttributes();
  }
  
  /**
   * Retrieve the attribute at a given index.
   *
   * @param index - the index to look up.
   * @return The attribute at that index.
   */
  public Attribute get(int index) {
    if (size() == 0) throw new IllegalStateException("Attribute list is empty.");
    return new Attribute(attributes.getCompound(index));
  }
  
  // We can't make Attributes itself iterable without splitting it up into separate classes
  public Iterable<Attribute> values() {
    return () -> {
      // Handle the empty case
      if (size() == 0) return Collections.<Attribute>emptyList().iterator();
      return Iterators.transform(attributes.iterator(),
          element -> new Attribute((NbtCompound) element));
    };
  }
}
