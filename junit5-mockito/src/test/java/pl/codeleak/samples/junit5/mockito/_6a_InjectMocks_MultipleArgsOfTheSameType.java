package pl.codeleak.samples.junit5.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class _6a_InjectMocks_MultipleArgsOfTheSameType {

    static class SomeClass {
        Dep rootDep;
        Dep otherDep;
        Dep yetAnotherDep;

//        SomeClass(Dep rootDep, Dep otherDep, Dep yetAnotherDep) {
//            this.rootDep = rootDep;
//            this.otherDep = otherDep;
//            this.yetAnotherDep = yetAnotherDep;
//        }
    }

    static class Dep {}

    @InjectMocks
    SomeClass someClass;

    @Mock(name = "rootDep")
    Dep dep1;

    @Mock(name = "otherDep")
    Dep dep2;

    @Mock(name = "yetAnotherDep")
    Dep dep3;

    @Test
    void injectsMocksInProperOrder() {
        assertAll(
                () -> assertSame(someClass.rootDep, this.dep1),
                () -> assertSame(someClass.otherDep, this.dep2),
                () -> assertSame(someClass.yetAnotherDep, this.dep3)
        );
    }
}