import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MemoryBlock {
    int startAddress;
    int size;
    boolean allocated;

    public MemoryBlock(int startAddress, int size) {
        this.startAddress = startAddress;
        this.size = size;
        this.allocated = false;
    }
}

class WorstFitMemoryManager {
    List<MemoryBlock> memory;

    public WorstFitMemoryManager(List<MemoryBlock> memory) {
        this.memory = memory;
    }

    public void allocate(int processSize) {
        MemoryBlock worstFitBlock = null;

        for (MemoryBlock block : memory) {
            if (!block.allocated && block.size >= processSize) {
                if (worstFitBlock == null || block.size > worstFitBlock.size) {
                    worstFitBlock = block;
                }
            }
        }

        if (worstFitBlock != null) {
            worstFitBlock.allocated = true;
            System.out.println("Allocated process of size " + processSize + " at address " + worstFitBlock.startAddress);
        } else {
            System.out.println("Not enough memory to allocate process of size " + processSize);
        }
    }

    public void deallocate(int address) {
        for (MemoryBlock block : memory) {
            if (block.startAddress == address) {
                block.allocated = false;
                System.out.println("Deallocated process at address " + address);
                return;
            }
        }
        System.out.println("No process found at address " + address);
    }
}

public class WorstFit {
    public static void main(String[] args) {
        List<MemoryBlock> memory = new ArrayList<>();
        memory.add(new MemoryBlock(0, 100));
        memory.add(new MemoryBlock(100, 50));
        memory.add(new MemoryBlock(150, 200));
        memory.add(new MemoryBlock(350, 300));

        WorstFitMemoryManager memoryManager = new WorstFitMemoryManager(memory);

        memoryManager.allocate(80);
        memoryManager.allocate(100);
        memoryManager.allocate(150);
        memoryManager.allocate(50);

        memoryManager.deallocate(100);

        memoryManager.allocate(50);
        memoryManager.allocate(120);

        memoryManager.deallocate(150);
    }
}
