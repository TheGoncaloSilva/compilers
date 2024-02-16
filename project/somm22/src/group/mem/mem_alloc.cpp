/*
 *  \author Tiago Silvestre
 */

#include "somm22.h"
#include "mem_module.h"
#include <iostream>
#include <algorithm>
namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void *memAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(403, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");
            void* addr = NULL;

            if(size % mem::chunkSize != 0) //(prevent multiples of chunk size values shift by mem::chunkSize positions)
                size += mem::chunkSize - (size % mem::chunkSize);

            switch(mem::allocationPolicy)
            {
                case FirstFit: addr = memFirstFitAlloc(pid , size); break;
                case NextFit:  addr = memNextFitAlloc (pid , size); break;
                case BestFit:  addr = memBestFitAlloc (pid , size); break;
                case WorstFit: addr = memWorstFitAlloc(pid , size); break;

                default:
                    throw Exception(EINVAL , "Unknown allocation policy");
            }
            require((uintptr_t)addr % mem::chunkSize == 0 , "Allocation should be multiple of mem::chunkSize");

            if(addr == NULL) //memory alocation failed
                return NULL;

            //insert into allocated data
            mem::block _block = {pid , addr , size};
            auto it = std::upper_bound(mem::memAllocated.begin() , mem::memAllocated.end() , _block , [](mem::block a , mem::block b) -> bool{return (intptr_t)a.initialAddr + a.size < (intptr_t)b.initialAddr;});
            mem::memAllocated.insert(it , _block);

            //refresh free space
            mem::lastBlock->initialAddr = (void*)((intptr_t)addr + (intptr_t)size);
            mem::lastBlock->size -= size;

            return addr;

        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

