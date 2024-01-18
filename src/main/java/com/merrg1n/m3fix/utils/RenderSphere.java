package com.merrg1n.m3fix.utils;


import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class RenderSphere {
    static int vertices_count;
    static int indexes_count;
    static FloatBuffer buffered_vertices;
    static FloatBuffer buffered_normals;
    static FloatBuffer buffered_uv;
    static IntBuffer buffered_indexes;

    static {
        try (InputStream s = RenderSphere.class.getResourceAsStream("/sphere.model")) {
            assert s != null;
            BufferedReader r = new BufferedReader(new InputStreamReader(s));
            int[] size = Arrays.stream(r.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            vertices_count = size[0];
            r.readLine();
            buffered_vertices = BufferUtils.createFloatBuffer(vertices_count * 3);
            for (int i = 0; i < vertices_count; i++) {
                Object[] o = Arrays.stream(r.readLine().split(" ")).map(Float::parseFloat).toArray();
                buffered_vertices.put((Float) o[0]);
                buffered_vertices.put((Float) o[1]);
                buffered_vertices.put((Float) o[2]);
            }
            buffered_vertices.position(0);
            r.readLine();
            buffered_normals = BufferUtils.createFloatBuffer(vertices_count * 3);
            for (int i = 0; i < vertices_count; i++) {
                Object[] o = Arrays.stream(r.readLine().split(" ")).map(Float::parseFloat).toArray();
                buffered_normals.put((Float) o[0]);
                buffered_normals.put((Float) o[1]);
                buffered_normals.put((Float) o[2]);
            }
            buffered_normals.position(0);
            r.readLine();
            buffered_uv = BufferUtils.createFloatBuffer(vertices_count * 2);
            for (int i = 0; i < vertices_count; i++) {
                Object[] o = Arrays.stream(r.readLine().split(" ")).map(Float::parseFloat).toArray();
                buffered_uv.put((Float) o[0]);
                buffered_uv.put((Float) o[1]);
            }
            buffered_uv.position(0);
            r.readLine();
            indexes_count = size[1] * 3;
            buffered_indexes = BufferUtils.createIntBuffer(indexes_count);
            for (int i = 0; i < size[1]; i++) {
                buffered_indexes.put(Arrays.stream(r.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            }
            buffered_indexes.position(0);
            r.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isInited = false;

    static int vbo;
    static int ibo;

    static void init() {
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, (long) vertices_count * (3 + 2) * 4, GL_STATIC_DRAW);
        glBufferSubData(GL_ARRAY_BUFFER, 0L, buffered_vertices);
        glBufferSubData(GL_ARRAY_BUFFER, (long) vertices_count * (3) * 4, buffered_uv);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffered_indexes, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        isInited = true;
    }

    public static void render(float size) {
        if (!isInited) {
            init();
        }
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glEnableClientState(GL_VERTEX_ARRAY);
        glVertexPointer(3, GL_FLOAT, 0, 0);

        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glTexCoordPointer(2, GL_FLOAT, 0, (long) vertices_count * (3) * 4);

        glScalef(size, size, size);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, indexes_count, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);
    }
}
