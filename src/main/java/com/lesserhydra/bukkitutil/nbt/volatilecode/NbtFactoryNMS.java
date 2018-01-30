package com.lesserhydra.bukkitutil.nbt.volatilecode;

import com.lesserhydra.bukkitutil.nbt.NbtBase;
import com.lesserhydra.bukkitutil.nbt.NbtByte;
import com.lesserhydra.bukkitutil.nbt.NbtByteArray;
import com.lesserhydra.bukkitutil.nbt.NbtCompound;
import com.lesserhydra.bukkitutil.nbt.NbtDouble;
import com.lesserhydra.bukkitutil.nbt.NbtFloat;
import com.lesserhydra.bukkitutil.nbt.NbtInt;
import com.lesserhydra.bukkitutil.nbt.NbtIntArray;
import com.lesserhydra.bukkitutil.nbt.NbtLong;
import com.lesserhydra.bukkitutil.nbt.NbtLongArray;
import com.lesserhydra.bukkitutil.nbt.NbtShort;
import com.lesserhydra.bukkitutil.nbt.NbtString;
import com.lesserhydra.bukkitutil.nbt.NbtType;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagByte;
import net.minecraft.server.v1_12_R1.NBTTagByteArray;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagDouble;
import net.minecraft.server.v1_12_R1.NBTTagFloat;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagIntArray;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagLong;
import net.minecraft.server.v1_12_R1.NBTTagLongArray;
import net.minecraft.server.v1_12_R1.NBTTagShort;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.jetbrains.annotations.Nullable;


public class NbtFactoryNMS {
  
  public static CraftNbtList makeList() {
    return new CraftNbtList(new NBTTagList());
  }
  
  public static CraftNbtCompound makeCompound() {
    return new CraftNbtCompound(new NBTTagCompound());
  }
  
  public static NbtBase wrapTag(@Nullable NBTBase tag) {
    if (tag == null) return null;
    switch(NbtType.fromId(tag.getTypeId())) {
    case BYTE: return new NbtByte(((NBTTagByte)tag).g());
    case SHORT: return new NbtShort(((NBTTagShort)tag).f());
    case INT: return new NbtInt(((NBTTagInt)tag).e());
    case LONG: return new NbtLong(((NBTTagLong)tag).d());
    case FLOAT: return new NbtFloat(((NBTTagFloat)tag).i());
    case DOUBLE: return new NbtDouble(((NBTTagDouble)tag).asDouble());
    case BYTE_ARRAY: return new NbtByteArray(((NBTTagByteArray)tag).c());
    case STRING: return new NbtString(((NBTTagString)tag).c_());
    case LIST: return new CraftNbtList((NBTTagList) tag);
    case COMPOUND: return new CraftNbtCompound((NBTTagCompound) tag);
    case INT_ARRAY: return new NbtIntArray(((NBTTagIntArray)tag).d());
    case LONG_ARRAY: return new NbtLongArray(new long[0]); //TODO: No way to access storage?
    default: return null;
    }
  }
  
  public static NBTBase unwrapTag(NbtBase tag) {
    if (tag == null) return null;
    switch(tag.getType()) {
    case BYTE: return new NBTTagByte(((NbtByte)tag).asByte());
    case SHORT: return new NBTTagShort(((NbtShort)tag).asShort());
    case INT: return new NBTTagInt(((NbtInt)tag).asInt());
    case LONG: return new NBTTagLong(((NbtLong)tag).asLong());
    case FLOAT: return new NBTTagFloat(((NbtFloat)tag).asFloat());
    case DOUBLE: return new NBTTagDouble(((NbtDouble)tag).asDouble());
    case BYTE_ARRAY: return new NBTTagByteArray(((NbtByteArray)tag).getByteArray());
    case STRING: return new NBTTagString(((NbtString)tag).getString());
    case LIST: return ((CraftNbtList) tag).getHandle();
    case COMPOUND: return ((CraftNbtCompound) tag).getHandle();
    case INT_ARRAY: return new NBTTagIntArray(((NbtIntArray)tag).getIntArray());
    case LONG_ARRAY: return new NBTTagLongArray(((NbtLongArray)tag).getLongArray());
    default: return null;
    }
  }
}
