/*
 *  \author Diogo Matos, Tiago Silvestre
 */

#include "somm22.h"
#include "mem_module.h"

#include <vector>
#include <algorithm>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void *memNextFitAlloc(uint32_t pid, uint32_t size)
        {
            soProbe(405, "%s(%u, 0x%x)\n", __func__, pid, size);

            require(pid > 0, "process ID must be non-zero");
            
            auto has_addr = [](mem::block b){ return b.initialAddr >= mem::lastAllocation || (void*)((intptr_t)b.initialAddr+b.size)>mem::lastAllocation; };
            auto index = std::find_if(mem::memFree.begin(), mem::memFree.end(), has_addr); //find first block after last allocation
            bool reach_end = false;
            bool prevent_increment = false; 


            for (auto it = index; it != mem::memFree.end(); ++it){
                if(prevent_increment) //when iterator goes back to begin(), ++it should be reversed
                {
                    it = std::prev(it);
                    prevent_increment = false;
                }
                if(((long)it->size - (long)(size)) >= 0){
                    mem::lastAllocation = it->initialAddr;
                    mem::lastBlock = it;
                    return it->initialAddr;

                } else if(std::next(it) == mem::memFree.end() && !reach_end){ //circular behavior
                    reach_end = true;
                    it = mem::memFree.begin();
                    prevent_increment = true;
                }
            }
            
            return NULL;
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
