package com.github.sa1nt.ibmheapdump;

import org.springframework.boot.actuate.endpoint.mvc.HeapdumpMvcEndpoint;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class IBMHeapdumpMvcEndpoint extends HeapdumpMvcEndpoint {
    public IBMHeapdumpMvcEndpoint() {
        super(TimeUnit.SECONDS.toMillis(10));
    }

    @Override
    protected HeapDumper createHeapDumper() throws HeapDumperUnavailableException {
        try {
            ClassUtils.resolveClassName("com.ibm.jvm.Dump", null);
            return new IbmJvmHeapDumper();
        } catch (IllegalArgumentException e) {
            return super.createHeapDumper();
        }
    }

    static class IbmJvmHeapDumper implements HeapDumper {

        private final Method heapDumpToFileMethod;

        IbmJvmHeapDumper() {
            Class<?> ibmDumpClass = ClassUtils.resolveClassName("com.ibm.jvm.Dump", null);
            this.heapDumpToFileMethod = ReflectionUtils.findMethod(ibmDumpClass, "heapDumpToFile", String.class);
        }

        public void dumpHeap(File file, boolean live) {
            ReflectionUtils.invokeMethod(heapDumpToFileMethod, null, file.getAbsolutePath());
        }
    }
}
