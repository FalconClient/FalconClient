package net.minecraft.util;

public class Vec3
{
    /** X coordinate of Vec3D */
    public final double xCoord;

    /** Y coordinate of Vec3D */
    public final double yCoord;

    /** Z coordinate of Vec3D */
    public final double zCoord;

    public Vec3(double x, double y, double z)
    {
        x = (x == -0.0D) ? 0.0D : x;
        y = (y == -0.0D) ? 0.0D : y;
        z = (z == -0.0D) ? 0.0D : z;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }

    public Vec3(final Vec3i p_i46377_1_)
    {
        this(p_i46377_1_.getX(), p_i46377_1_.getY(), p_i46377_1_.getZ());
    }

    /**
     * Returns a new vector with the result of the specified vector minus this.
     */
    public Vec3 subtractReverse(final Vec3 vec)
    {
        return new Vec3(vec.xCoord - this.xCoord, vec.yCoord - this.yCoord, vec.zCoord - this.zCoord);
    }

    /**
     * Normalizes the vector to a length of 1 (except if it is the zero vector)
     */
    public Vec3 normalize()
    {
        final double d0 = MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
        return d0 < 1.0E-4D ? new Vec3(0.0D, 0.0D, 0.0D) : new Vec3(this.xCoord / d0, this.yCoord / d0, this.zCoord / d0);
    }

    public double dotProduct(final Vec3 vec)
    {
        return this.xCoord * vec.xCoord + this.yCoord * vec.yCoord + this.zCoord * vec.zCoord;
    }

    /**
     * Returns a new vector with the result of this vector x the specified vector.
     */
    public Vec3 crossProduct(final Vec3 vec)
    {
        return new Vec3(this.yCoord * vec.zCoord - this.zCoord * vec.yCoord, this.zCoord * vec.xCoord - this.xCoord * vec.zCoord, this.xCoord * vec.yCoord - this.yCoord * vec.xCoord);
    }

    public Vec3 subtract(final Vec3 vec)
    {
        return this.subtract(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    public Vec3 subtract(final double x, final double y, final double z)
    {
        return this.addVector(-x, -y, -z);
    }

    public Vec3 add(final Vec3 vec)
    {
        return this.addVector(vec.xCoord, vec.yCoord, vec.zCoord);
    }

    /**
     * Adds the specified x,y,z vector components to this vector and returns the resulting vector. Does not change this
     * vector.
     */
    public Vec3 addVector(final double x, final double y, final double z)
    {
        return new Vec3(this.xCoord + x, this.yCoord + y, this.zCoord + z);
    }

    /**
     * Euclidean distance between this and the specified vector, returned as double.
     */
    public double distanceTo(final Vec3 vec)
    {
        return MathHelper.sqrt_double((vec.xCoord - this.xCoord) * (vec.xCoord - this.xCoord) +
                (vec.yCoord - this.yCoord) * (vec.yCoord - this.yCoord) +
                (vec.zCoord - this.zCoord) * (vec.zCoord - this.zCoord));
    }

    /**
     * The square of the Euclidean distance between this and the specified vector.
     */
    public double squareDistanceTo(final Vec3 vec)
    {
        return (vec.xCoord - this.xCoord) * (vec.xCoord - this.xCoord) +
                (vec.yCoord - this.yCoord) * (vec.yCoord - this.yCoord) +
                (vec.zCoord - this.zCoord) * (vec.zCoord - this.zCoord);
    }

    /**
     * Returns the length of the vector.
     */
    public double lengthVector()
    {
        return MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
    }

    /**
     * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    public Vec3 getIntermediateWithXValue(final Vec3 vec, final double x)
    {
        final double d0 = vec.xCoord - this.xCoord;
        if (d0 * d0 < 1.0000000116860974E-7D)
            return null;
        else
        {
            final double d1 = vec.yCoord - this.yCoord;
            final double d2 = vec.zCoord - this.zCoord;
            final double d3 = (x - this.xCoord) / d0;
            return d3 >= 0.0D && d3 <= 1.0D ? new Vec3(this.xCoord + d0 * d3, this.yCoord + d1 * d3, this.zCoord + d2 * d3) : null;
        }




    }

    /**
     * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    public Vec3 getIntermediateWithYValue(final Vec3 vec, final double y)
    {

        double d1 = vec.yCoord - this.yCoord;
        if (d1 * d1 < 1.0000000116860974E-7D)
            return null;
        else
        {
            double d0 = vec.xCoord - this.xCoord;
            double d2 = vec.zCoord - this.zCoord;
            double d3 = (y - this.yCoord) / d1;
            return d3 >= 0.0D && d3 <= 1.0D ? new Vec3(this.xCoord + d0 * d3, this.yCoord + d1 * d3, this.zCoord + d2 * d3) : null;
        }





    }

    /**
     * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
     * passed in vector, or null if not possible.
     */
    public Vec3 getIntermediateWithZValue(final Vec3 vec, final double z)
    {
        double d2 = vec.zCoord - this.zCoord;
        if (d2 * d2 < 1.0000000116860974E-7D)
        {
            return null;
        }
        else
        {
            double d0 = vec.xCoord - this.xCoord;
            double d1 = vec.yCoord - this.yCoord;
            double d3 = (z - this.zCoord) / d2;
            return d3 >= 0.0D && d3 <= 1.0D ? new Vec3(this.xCoord + d0 * d3, this.yCoord + d1 * d3, this.zCoord + d2 * d3) : null;
        }
    }

    public String toString()
    {
        return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
    }

    public Vec3 rotatePitch(final float pitch)
    {
        float f = MathHelper.cos(pitch);
        float f1 = MathHelper.sin(pitch);
        return new Vec3(this.xCoord, this.yCoord * (double)f + this.zCoord * (double)f1, this.zCoord * (double)f - this.yCoord * (double)f1);
    }

    public Vec3 rotateYaw(final float yaw)
    {
        float f = MathHelper.cos(yaw);
        float f1 = MathHelper.sin(yaw);
        return new Vec3(this.xCoord * (double)f + this.zCoord * (double)f1, this.yCoord, this.zCoord * (double)f - this.xCoord * (double)f1);
    }
}