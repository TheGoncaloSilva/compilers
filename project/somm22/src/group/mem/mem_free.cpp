/*
 *  \author Diogo Matos
 */

#include "somm22.h"
#include "mem_module.h"

#include <algorithm>
#include <vector>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void memFree(void *addr)
        {
            soProbe(408, "%s(addr: %p)\n", __func__, addr);
            uint32_t p_size = 0;
            require(addr != NULL, "addr must be non-null");
            auto has_addr = [addr](mem::block b){ return b.initialAddr == addr; };

            auto index = std::find_if(mem::memAllocated.begin(), mem::memAllocated.end(), has_addr);
            
            //delete block from alloc list
            if (index != mem::memAllocated.end()){
                p_size = index->size;
                mem::memAllocated.erase(index);
            } else{
                //throw ?
                return;
            }
            //(uint32_t)static_cast<int>(reinterpret_cast<intptr_t>(memAddr))
            bool free = false;
            
            auto it = mem::memFree.begin();
            void* addrPlusSize = (void *)((intptr_t)(addr) + (intptr_t)p_size);
            for(; it != std::prev(mem::memFree.end()) && it->initialAddr < addrPlusSize; it++){
                bool mergeLeft = ((void *)((intptr_t)(it->initialAddr) + (intptr_t)it->size)) == addr;
                bool mergeRight = std::next(it)->initialAddr == addrPlusSize;

                if(mergeLeft && mergeRight)
                {
                    it->size += p_size + std::next(it)->size;
                    mem::memFree.erase(std::next(it)); 
                    free = true;
                    break;
                }
                else if(mergeLeft){
                    it->size += p_size;
                    free = true;
                    break;
                }
                else if(mergeRight)
                {
                    std::next(it)->initialAddr = addr;
                    std::next(it)->size += p_size;
                    free = true;
                    break;
                }
                
            }

            if(it->initialAddr == addrPlusSize){
                it->size += p_size;
                it->initialAddr = addr;
                free = true;
            }

            //if merge cannot be done
            if(!free) mem::memFree.insert(it,{0,addr,p_size});
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

