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

        void *memFirstFitAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(404, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");


            for (auto it = mem::memFree.begin() ; it != mem::memFree.end(); ++it){
                if(((long)it->size - (long)size) >= 0){
                    mem::lastBlock = it;
                    return it->initialAddr;
                }
            }

            return NULL;
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
