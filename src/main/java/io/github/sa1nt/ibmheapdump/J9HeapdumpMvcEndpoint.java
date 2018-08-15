package io.github.sa1nt.ibmheapdump;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.actuate.endpoint.mvc.HeapdumpMvcEndpoint;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class J9HeapdumpMvcEndpoint extends HeapdumpMvcEndpoint {
    private static final Log logger = LogFactory.getLog(J9HeapdumpMvcEndpoint.class);

    private static final String DUMP_CLASS_NAME = "com.ibm.jvm.Dump";
    private static final String DUMP_METHOD_NAME = "heapDumpToFile";

    public J9HeapdumpMvcEndpoint() {
        super(TimeUnit.SECONDS.toMillis(10));
    }

    @Override
    protected HeapDumper createHeapDumper() throws HeapDumperUnavailableException {
        try {
            Class<?> ibmDumpClass = ClassUtils.forName(DUMP_CLASS_NAME, null);
            Method ibmDumpMethod = ReflectionUtils.findMethod(ibmDumpClass, DUMP_METHOD_NAME, String.class);
            logger.debug("Initializing J9 HeapDumper");
            return new J9JvmHeapDumper(ibmDumpMethod);
        } catch (ClassNotFoundException e) {
            logger.debug("Initializing default HeapDumper");
            return super.createHeapDumper();
        }
    }

    static class J9JvmHeapDumper implements HeapDumper {
        private final Method dumpMethod;

        J9JvmHeapDumper(Method dumpMethod) {
            this.dumpMethod = dumpMethod;
        }

        public void dumpHeap(File file, boolean live) {
            ReflectionUtils.invokeMethod(dumpMethod, null, file.getAbsolutePath());
        }
    }
}
