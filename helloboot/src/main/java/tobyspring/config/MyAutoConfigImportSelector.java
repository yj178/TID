package tobyspring.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // config 후보에서 어떤 것들이 사용될 것인지는 스마트한 방법을 활용할 것
        // 클래스 로더 : 패스에서 리소스를 읽어올 때, 클래스 로더를 사용한다.,
        // 빈을 생산하기 위해서 스프링 컨테이너가 빈을 생산하기 위해서 빈 클래스를 로딩할 때 사용하는 그 클래스 로더

        // 클래스 로더를 어떻게? 애플리케이션 컨텍스 가 필요하다면 애플리케이션 컨텍스트 어웨어를 구현하고 세터를 이용해서 컨테이너가 빈을 초기화하면서 오브젝트에 빈을 주입한다.
        // 빈 클래스 로더?
        //

//        return new String[]{
//                "tobyspring.config.autoconfig.TomcatWebServerConfig",
//                "tobyspring.config.autoconfig.DispatcherServletConfig"
//        };


        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

//        List<String> autoConfigs = new ArrayList<>();

        // java 8
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(cadidate -> autoConfigs.add(cadidate));
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

////      java 5
//        for (String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
//            autoConfigs.add(candidate);
//        }
//        return autoConfigs.toArray(new String[0]); // java 5
//        return autoConfigs.stream().toArray(String[]::new); // java 8
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class); // java 8
    }
}
