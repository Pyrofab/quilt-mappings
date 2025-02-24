package quilt.internal.tasks.build;

import org.jetbrains.annotations.VisibleForTesting;
import quilt.internal.Constants;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;

public class MergeTinyV2Task extends AbstractHashedMergeTask {
    public static final String TASK_NAME = "mergeTinyV2";

    public MergeTinyV2Task() {
        super("merged2.tiny");
        dependsOn(InvertPerVersionMappingsTask.TASK_NAME, "v2UnmergedMappingsJar");

        input.convention(() -> this.<AddProposedMappingsTask>getTaskByName("insertAutoGeneratedMappings").getOutputMappings());
    }

    @VisibleForTesting
    public static void mergeMappings(Path insertAutoGeneratedMappingsOutput, Path invertedPerVersionsMappings, Path outputMappings) throws IOException {
        AbstractTinyMergeTask.mergeMappings(insertAutoGeneratedMappingsOutput, invertedPerVersionsMappings, outputMappings,
            Function.identity(), Function.identity(), Map.of("named", Constants.PER_VERSION_MAPPINGS_NAME));
    }
}
