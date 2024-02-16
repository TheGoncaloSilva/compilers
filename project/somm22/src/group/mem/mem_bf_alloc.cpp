/*
 *  \author Gonçalo Silva
 */

#include "somm22.h"
#include "mem_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void *memBestFitAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(406, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");

            int32_t sizeDiff = mem::maxSize+1;
            void* addr = nullptr;

            for (auto it = mem::memFree.begin() ; it != mem::memFree.end(); ++it){
                int32_t tempDiff = (long)it->size - (long)(size);
                if(tempDiff >= 0 && tempDiff < sizeDiff){
                    sizeDiff = tempDiff;
                    addr = it->initialAddr;
                    mem::lastBlock = it;
                }
            }

            return addr;

        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
